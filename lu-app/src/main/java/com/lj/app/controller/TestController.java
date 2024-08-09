package com.lj.app.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.lj.common_web.annotation.ResponseResultVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luojing
 * @since 2024/8/9 18:42
 */
@RestController
@ResponseResultVo
@RequestMapping("/test/")
public class TestController {

    @RequestMapping("isLogin")
    @SaIgnore
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }
}
