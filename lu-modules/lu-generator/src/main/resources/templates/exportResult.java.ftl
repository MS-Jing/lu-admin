package ${exportResult.packagePath};

import ${entity.packagePath}.${entity.className};
import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;
<#list exportResult.packages as pkg>
import ${pkg};
</#list>

/**
 * <p>
 * ${tableComment!} excel导出参数
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
public class ${exportResult.className} {
<#list fieldInfoList as field>
    <#if field.showInExport>

    <#if field.comment!?length gt 0>
    /**
     * ${field.comment}
     */
    </#if>
    <#if field.enumDict??>
    @ExcelProperty(value = "<#if field.comment!?length gt 0>${field.comment}</#if>", converter = CommentEnumConverter.class)
    <#elseif field.comment!?length gt 0>
    @ExcelProperty("${field.comment}")
    </#if>
    private <#if field.enumDict??>${field.enumDict.simpleClassName}<#else>${field.fieldType}</#if> ${field.fieldName};
    </#if>
</#list>

    public static ${exportResult.className} of(${entity.className} entity) {
        if (entity == null) {
            return null;
        }
        ${exportResult.className} result = new ${exportResult.className}();
        <#list fieldInfoList as field>
        <#if field.showInExport>
        result.set${field.fieldName?cap_first}(entity.get${field.fieldName?cap_first}());
        </#if>
        </#list>
        return result;
    }
}
