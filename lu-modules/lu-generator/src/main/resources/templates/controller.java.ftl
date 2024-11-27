package ${controller.packagePath};

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lj.common_web.annotation.ResponseResultVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import ${service.packagePath}.${service.className};
import jakarta.annotation.Resource;
<#if genPage || genInfo || genSave || genUpdate || genDeleted>
import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.Operation;
</#if>
<#if genPage || genInfo>
import org.springframework.web.bind.annotation.GetMapping;
</#if>
<#if genPage || genExport>
import org.springdoc.core.annotations.ParameterObject;
</#if>
<#if genInfo>
import org.springframework.web.bind.annotation.PathVariable;
</#if>
<#if genInfo || genImport>
import io.swagger.v3.oas.annotations.Parameter;
</#if>
<#if genSave || genUpdate>
import org.springframework.validation.annotation.Validated;
</#if>
<#if genSave || genUpdate || genDeleted>
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
</#if>
<#if genDeleted>
import java.util.List;
import cn.hutool.core.collection.CollUtil;
</#if>
<#list service.packages as pkg>
import ${pkg};
</#list>
<#list controller.packages as pkg>
import ${pkg};
</#list>
<#if genImport>
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
</#if>
<#if genImport || genExport>
import java.io.IOException;
import com.lj.common_web.utils.ServletUtil;
</#if>

/**
 * <p>
 * ${tableComment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@RestController
@RequestMapping("/${moduleName}/${controller.url}")
@ResponseResultVo
@Tag(name = "${tableComment!} 管理")
public class ${controller.className} {

    @Resource
    private ${service.className} ${service.className?uncap_first};

    <#if genPage>
    @GetMapping("/page")
    @SaCheckPermission("${moduleName}:${controller.url}:list")
    @Operation(summary = "${tableComment!} 分页查询")
    public IPage<${pageResult.className}> page(@ParameterObject ${pageParam.className} param) {
        return ${service.className?uncap_first}.page(param);
    }
    </#if>

    <#if genInfo>
    @GetMapping("/info/{id}")
    @SaCheckPermission("${moduleName}:${controller.url}:info")
    @Operation(summary = "${tableComment!} 信息")
    public ${infoResult.className} info(@PathVariable("id") @Parameter(name = "id", description = "${tableComment!} id") ${pkType} id) {
        if (id == null) {
            return null;
        }
        return ${service.className?uncap_first}.info(id);
    }
    </#if>

    <#if genSave>
    @PostMapping("/save")
    @SaCheckPermission("${moduleName}:${controller.url}:save")
    @Operation(summary = "${tableComment!} 新增")
    public void save(@RequestBody @Validated ${saveParam.className} param) {
        ${service.className?uncap_first}.save(param);
    }
    </#if>

    <#if genUpdate>
    @PostMapping("/update")
    @SaCheckPermission("${moduleName}:${controller.url}:update")
    @Operation(summary = "${tableComment!} 更新")
    public void update(@RequestBody @Validated ${updateParam.className} param) {
        ${service.className?uncap_first}.update(param);
    }
    </#if>

    <#if genDeleted>
    @PostMapping("/delete")
    @SaCheckPermission("${moduleName}:${controller.url}:delete")
    @Operation(summary = "${tableComment!} 删除")
    public void delete(@RequestBody List<${pkType}> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        ${service.className?uncap_first}.removeByIds(ids);
    }
    </#if>

    <#if genImport>
    @PostMapping("/import/excel")
    @SaCheckPermission("${moduleName}:${controller.url}:save")
    @Operation(summary = "${tableComment!} excel导入")
    public void importExcel(@Parameter(description = "导入excel文件") @RequestParam("file") MultipartFile file) throws IOException {
        userService.importExcel(file.getInputStream());
    }

    @GetMapping("/import/template")
    @SaCheckPermission("${moduleName}:${controller.url}:save")
    @Operation(summary = "${tableComment!} excel导入模板")
    public void importTemplate() throws IOException {
        userService.importExcelTemplate(ServletUtil.getResponse().getOutputStream());
        ServletUtil.setFileResponseHeader("${tableComment!}-导入模板.xlsx");
    }
    </#if>

    <#if genExport>
    @GetMapping("/export/excel")
    @SaCheckPermission("${moduleName}:${controller.url}:list")
    @Operation(summary = "${tableComment!} excel导出")
    public void exportExcel(@ParameterObject ${pageParam.className} param) throws IOException {
        userService.exportExcel(param, ServletUtil.getResponse().getOutputStream());
        ServletUtil.setFileResponseHeader("${tableComment!}-导出数据.xlsx");
    }
    </#if>

}

