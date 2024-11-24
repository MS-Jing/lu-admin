package com.lj.generator.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.common_web.utils.ServletUtil;
import com.lj.generator.params.GenTableConfigPageParams;
import com.lj.generator.params.GenTableConfigSaveOrUpdateParams;
import com.lj.generator.result.GenPreviewResult;
import com.lj.generator.result.GenTableConfigPageResult;
import com.lj.generator.result.TableInfoResult;
import com.lj.generator.service.GenTableConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("/pageQuery")
    @SaCheckPermission("gen:table:list")
    @Operation(summary = "配置表分页", description = "配置表分页查询")
    public IPage<GenTableConfigPageResult> pageQuery(@ParameterObject GenTableConfigPageParams pageParams) {
        return genTableConfigService.pageQuery(pageParams);
    }

    @PostMapping("/saveOrUpdate")
    @SaCheckPermission(value = {"gen:table:save", "gen:table:update"}, mode = SaMode.OR)
    @Operation(summary = "新增或者更新表配置")
    public void saveOrUpdate(@RequestBody @Validated GenTableConfigSaveOrUpdateParams params) {
        genTableConfigService.saveOrUpdate(params);
    }

    @GetMapping("/preview")
    @SaCheckPermission("gen:table:info")
    @Operation(summary = "预览表生成", description = "生成的内容预览")
    public List<GenPreviewResult> preview(Long tableId) {
        return genTableConfigService.preview(tableId);
    }

    @GetMapping("/generate")
    @SaCheckPermission("gen:table:info")
    @Operation(summary = "生成表", description = "生成表")
    public void generate(Long tableId) throws IOException {
        HttpServletResponse response = ServletUtil.getResponse();
        String fileName = genTableConfigService.generate(tableId, response.getOutputStream());
        ServletUtil.setFileResponseHeader(fileName);
    }
}
