package com.lj.app.controller;

import com.lj.common_web.annotation.ResponseResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luojing
 * @since 2024/8/9 18:42
 */
@RestController
@ResponseResultVo
@RequestMapping("/test/")
@Tag(name = "测试api")
public class TestController {

    @GetMapping("/sayHello")
    @Operation(summary = "测试接口", description = "测试接口")
    public String isLogin() {
        return "sayHello";
    }
}
