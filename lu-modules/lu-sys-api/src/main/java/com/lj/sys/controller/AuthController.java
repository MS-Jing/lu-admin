package com.lj.sys.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.hutool.core.util.StrUtil;
import com.lj.common.exception.CommonException;
import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.common_web.utils.ServletUtil;
import com.lj.sys.params.LoginParams;
import com.lj.sys.service.SysAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
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
@Validated
public class AuthController {

    @Resource
    private SysAuthService authService;

    @GetMapping("captcha")
    @ApiOperation("获取一个验证码")
    @SaIgnore
    public void captcha(@ApiParam(value = "获取验证码的uuid", required = true) String uuid) throws IOException {
        if (StrUtil.isBlank(uuid)) {
            throw new CommonException("uuid不能为空");
        }
        HttpServletResponse response = ServletUtil.getResponse();
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/png");
        try (ServletOutputStream out = response.getOutputStream()) {
            authService.captcha(uuid, out);
        }
    }

    @PostMapping("/login")
    @ApiOperation("认证接口")
    public SaTokenInfo doLogin(@Validated @RequestBody LoginParams params) {
        return authService.doLogin(params.of());
    }

    @PostMapping("/logout")
    @ApiOperation("注销接口")
    public void logout() {
        authService.logout();
    }

}