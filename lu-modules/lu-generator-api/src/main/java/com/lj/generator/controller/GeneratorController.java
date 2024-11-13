package com.lj.generator.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.generator.entity.vo.TableInfoResult;
import com.lj.generator.service.GenTableConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
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
@Tag(name = "生成器管理")
public class GeneratorController {

    @Resource
    private GenTableConfigService genTableConfigService;

    @GetMapping("/load/table")
    @SaCheckPermission("gen:table:save")
    @Operation(summary = "加载表信息")
    public TableInfoResult loadTableInfo(@Parameter(description = "加载的表名") String tableName) {
        return genTableConfigService.loadTableInfo(tableName);
    }
}
