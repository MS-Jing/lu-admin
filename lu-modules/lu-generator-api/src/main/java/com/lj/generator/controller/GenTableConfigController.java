package com.lj.generator.controller;

import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.generator.service.GenTableConfigService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 生成器表配置 前端控制器
 * </p>
 *
 * @author lj
 * @since 2024-11-12
 */
@RestController
@ResponseResultVo
@RequestMapping("/generator/")
public class GenTableConfigController {

    @Resource
    private GenTableConfigService genTableConfigService;

}
