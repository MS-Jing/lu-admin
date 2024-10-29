package com.lj.sys.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.common_web.utils.ServletUtil;
import com.lj.sys.service.SysAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luojing
 * @since 2024/8/9 16:06
 */
@RestController
@ResponseResultVo
@RequestMapping("/sys/auth")
@Api(tags = "认证管理")
public class AuthController {

    @Resource
    private SysAuthService authService;

    @GetMapping("captcha")
    @ApiOperation("获取一个验证码")
    @SaIgnore
    public void captcha(@ApiParam(value = "获取验证码的uuid", required = true) @RequestParam String uuid) throws IOException {
        HttpServletResponse response = ServletUtil.getResponse();
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/png");
        try (ServletOutputStream out = response.getOutputStream()) {
            authService.captcha(uuid, out);
        }
    }

    @PostMapping("/login")
    @ApiOperation("认证接口")
    public SaResult doLogin(String username, String password) {
        // 第1步，先登录上
        StpUtil.login(10001);
        // 第2步，获取 Token  相关参数
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        // 第3步，返回给前端
        return SaResult.data(tokenInfo);
    }

    @GetMapping("/isLogin")
    @ApiOperation("是否已经认证")
    public Boolean isLogin() {
        return StpUtil.isLogin();
    }


}
