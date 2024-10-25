package com.lj.sys.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.lj.common_web.annotation.ResponseResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luojing
 * @since 2024/8/9 16:06
 */
@RestController
@ResponseResultVo
@RequestMapping("/sys/auth")
@Api(tags = "认证管理")
public class AuthController {

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
    public Boolean isLogin(){
        return StpUtil.isLogin();
    }


}
