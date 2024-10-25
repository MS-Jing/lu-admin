package com.lj.app.controller;

import com.lj.common_web.annotation.ResponseResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/sayHello")
    public String isLogin() {
        return "sayHello";
    }
}
