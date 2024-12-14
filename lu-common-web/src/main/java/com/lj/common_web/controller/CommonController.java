package com.lj.common_web.controller;

import com.lj.common.module.ModuleHelper;
import com.lj.common.module.ModuleInfo;
import com.lj.common_web.annotation.ResponseResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luojing
 * @since 2024/12/14 16:54
 */
@RestController
@RequestMapping("/common/")
@ResponseResultVo
@Tag(name = "公共接口")
public class CommonController {

    @Resource
    private ModuleHelper moduleHelper;

    @GetMapping("/modules")
    @Operation(summary = "模块信息", description = "当前引入的模块信息")
    public List<ModuleInfo> modules() {
        return moduleHelper.getModuleInfoList();
    }
}
