package com.lj.auth.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.hutool.core.util.StrUtil;
import com.lj.auth.params.LoginParams;
import com.lj.auth.service.SysAuthService;
import com.lj.common.exception.CommonException;
import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.common_web.utils.ServletUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author luojing
 * @since 2024/8/9 16:06
 */
@RestController
@ResponseResultVo
@RequestMapping("/sys/auth")
@Tag(name = "认证管理")
@Validated
public class AuthController {

    @Resource
    private SysAuthService authService;

    @GetMapping("captcha")
    @Operation(summary = "获取一个验证码")
    @SaIgnore
    public void captcha(@Parameter(description = "获取验证码的uuid", name = "uuid", required = true) String uuid) throws IOException {
        if (StrUtil.isBlank(uuid)) {
            throw new CommonException("uuid不能为空");
        }
        HttpServletResponse response = ServletUtil.getResponse();
        ServletUtil.setPngResponseHeader();
        try (ServletOutputStream out = response.getOutputStream()) {
            authService.captcha(uuid, out);
        }
    }

    @PostMapping("/login")
    @Operation(summary = "认证接口")
    public SaTokenInfo doLogin(@Validated @RequestBody LoginParams params) {
        return authService.doLogin(params);
    }

    @PostMapping("/logout")
    @Operation(summary = "注销接口")
    public void logout() {
        authService.logout();
    }

    // todo 修改密码

}
