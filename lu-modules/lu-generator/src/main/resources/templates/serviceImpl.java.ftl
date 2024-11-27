package ${serviceImpl.packagePath};

import ${entity.packagePath}.${entity.className};
import ${mapper.packagePath}.${mapper.className};
import ${service.packagePath}.${service.className};
import com.lj.mp.standard.StandardServiceImpl;
import org.springframework.stereotype.Service;
<#if genPage>
import com.lj.mp.utils.PageQueryUtils;
</#if>
<#list service.packages as pkg>
import ${pkg};
</#list>
<#if genImport>
import java.io.InputStream;
import java.util.Collections;
</#if>
<#if genImport || genExport>
import java.io.OutputStream;
import java.util.List;
import com.lj.common.utils.excel.ExcelSheet;
import com.lj.common.utils.excel.ExcelUtil;
</#if>
<#list serviceImpl.packages as pkg>
import ${pkg};
</#list>

/**
 * <p>
 * ${tableComment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${serviceImpl.className} extends StandardServiceImpl<${mapper.className}, ${entity.className}> implements ${service.className} {
    <#if genPage>

    @Override
    public IPage<${pageResult.className}> page(${pageParam.className} param) {
        return this.page(PageQueryUtils.getPage(param), getQueryWrapper(param)).convert(${pageResult.className}::of);
    }
    </#if>
    <#if genPage || genExport>

    private LambdaQueryWrapper<${entity.className}> getQueryWrapper(${pageParam.className} param) {
        // todo 查询条件
        return lambdaQueryWrapper();
    }
    </#if>
    <#if genInfo>

    @Override
    public ${infoResult.className} info(${pkType} id) {
        return ${infoResult.className}.of(this.getById(id));
    }
    </#if>
    <#if genSave>

    @Override
    public void save(${saveParam.className} param) {
        ${entity.className} entity = param.toEntity();
        this.save(entity);
    }
    </#if>
    <#if genUpdate>

    @Override
    public void update(${updateParam.className} param) {
        ${entity.className} entity = param.toEntity();
        this.updateById(entity);
    }
    </#if>
    <#if genImport>

    @Override
    public void importExcel(InputStream in) {
        List<${importParam.className}> paramList = ExcelUtil.readExcel(in, ${importParam.className}.class);
        List<${entity.className}> saveList = paramList.stream().map(${importParam.className}::toEntity).toList();
        this.insertBatchSomeColumn(saveList);
    }

    @Override
    public void importExcelTemplate(OutputStream out) {
        ExcelUtil.exportExcel(out, new ExcelSheet<>(Collections.emptyList(), ${importParam.className}.class));
    }
    </#if>
    <#if genExport>
    @Override
    public void exportExcel(${pageParam.className} param, OutputStream out) {
        List<${entity.className}> dataList = this.list(getQueryWrapper(param));
        List<${exportResult.className}> exportList = dataList.stream().map(${exportResult.className}::of).toList();
        ExcelUtil.exportExcel(out, new ExcelSheet<>(exportList, ${exportResult.className}.class));
    }
    </#if>
}
