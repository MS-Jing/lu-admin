package com.lj.auth.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.util.StrUtil;
import com.lj.auth.params.LoginParams;
import com.lj.auth.service.SysAuthService;
import com.lj.common.exception.CommonException;
import com.lj.common.utils.RedisUtil;
import com.lj.sys.entity.SysUser;
import com.lj.sys.enums.SysUserStatus;
import com.lj.sys.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author luojing
 * @since 2024/10/29 10:26
 */
@Service
public class SysAuthServiceImpl implements SysAuthService {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private SysUserService userService;

    /**
     * 验证码redis前缀
     */
    private static final String CAPTCHA_PREFIX = "captcha:";
    /**
     * 验证码过期时间 单位分钟
     */
    private static final Integer CAPTCHA_EXPIRES = 5;

    @Override
    public void captcha(String uuid, OutputStream out) {
        if (StrUtil.isBlank(uuid)) {
            throw new CommonException("uuid不能为空");
        }
        //定义图形验证码的长和宽
        ICaptcha captcha = CaptchaUtil.createLineCaptcha(200, 100);
        String code = captcha.getCode();
        // 将验证码存入redis,并设置过期时间
        if (!redisUtil.set(CAPTCHA_PREFIX + uuid, code, CAPTCHA_EXPIRES, TimeUnit.MINUTES)) {
            throw new CommonException("无法获取验证码！请联系管理员！");
        }
        captcha.write(out);
    }

    @Override
    public boolean validate(String uuid, String captcha) {
        if (StrUtil.isBlank(uuid)) {
            throw new CommonException("uuid不能为空");
        }
        String code = redisUtil.get(CAPTCHA_PREFIX + uuid, String.class);
        if (StrUtil.isBlank(code)) {
            throw new CommonException("验证码已失效!");
        }
        // 删除验证码
        redisUtil.delete(CAPTCHA_PREFIX + uuid);
        // 对比
        return StrUtil.equalsIgnoreCase(code, captcha);
    }

    @Override
    public SaTokenInfo doLogin(LoginParams params) {
        // 校验验证码
        if (!validate(params.getUuid(), params.getCaptcha())) {
            throw new CommonException("验证码不正确!");
        }
        SysUser sysUser = userService.getUserByUserName(params.getUserName());
        if (sysUser == null || !StrUtil.equalsIgnoreCase(sysUser.getPassword(), SaSecureUtil.sha256(params.getPassword()))) {
            throw new CommonException("用户名或密码不正确!");
        }
        if (!SysUserStatus.NORMAL.equals(sysUser.getUserStatus())) {
            throw new CommonException("当前账号处于 [" + sysUser.getUserStatus().getDesc() + "] 状态！");
        }
        // 登录
        StpUtil.login(sysUser.getId());
        // 获取token信息
        return StpUtil.getTokenInfo();
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }
}
