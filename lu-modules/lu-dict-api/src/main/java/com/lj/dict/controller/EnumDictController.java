package com.lj.dict.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.dict.params.DictQueryParams;
import com.lj.dict.service.EnumDictService;
import com.lj.dict.vo.EnumDictItem;
import com.lj.dict.vo.EnumDictVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/list")
    @SaCheckPermission("dict:enum:list")
    @Operation(summary = "获取枚举字典")
    public List<EnumDictVo> getDict(@ParameterObject DictQueryParams params) {
        return enumDictService.getDict(params.toDto());
    }

    @GetMapping("/item")
    @SaCheckPermission("dict:enum:list")
    @Operation(summary = "根据枚举字典名称获取字典项")
    public List<EnumDictItem<Object>> getItemByName(@Parameter(description = "枚举字典名称") String name) {
        return enumDictService.getDictItemByName(name);
    }

}
