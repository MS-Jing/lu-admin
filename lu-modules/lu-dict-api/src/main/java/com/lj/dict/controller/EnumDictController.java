package com.lj.dict.controller;

import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.dict.service.EnumDictService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luojing
 * @date 2024/11/15
 * 枚举字典控制器
 */
@RestController
@ResponseResultVo
@RequestMapping("/dict/enum")
@Tag(name = "枚举字典管理")
public class EnumDictController {

    @Resource
    private EnumDictService enumDictService;

    // 枚举查询list 是否是标准枚举字典 字典名称 字典值类型

    // 根据枚举名称查询字典


}
