package com.lj.generator.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.generator.result.TableInfoResult;
import com.lj.generator.service.GenTableConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/enable/table")
    @SaCheckPermission("gen:table:list")
    @Operation(summary = "获取可生成的表名", description = "新增时，如果表已经配置了无法再次创建，也就无法再次选择")
    public List<String> enableGenTable() {
        return genTableConfigService.enableGenTable();
    }

    @GetMapping("/load/table")
    @SaCheckPermission("gen:table:list")
    @Operation(summary = "加载表信息")
    public TableInfoResult loadTableInfo(@Parameter(description = "加载的表名") String tableName) {
        return genTableConfigService.loadTableInfo(tableName);
    }

    @GetMapping("/optional/superClass")
    @SaCheckPermission("gen:table:list")
    @Operation(summary = "选择父类", description = "生成的每一个表的实体都要选择一个父类")
    public List<String> optionalSuperClass() {
        return genTableConfigService.optionalSuperClass();
    }
}
