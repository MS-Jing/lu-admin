package com.lj.sys.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.util.StrUtil;
import com.lj.common.exception.CommonException;
import com.lj.common.utils.RedisUtil;
import com.lj.sys.service.SysAuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
