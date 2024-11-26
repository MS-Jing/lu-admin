package ${importParam.packagePath};

import ${entity.packagePath}.${entity.className};
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
<#list importParam.packages as pkg>
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
public class ${importParam.className} {
<#list fieldInfoList as field>
    <#if field.showInImport>

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
    private <#if field.enumDictFieldType??>${field.enumDictFieldType}<#else>${field.fieldType}</#if> ${field.fieldName};
    </#if>
</#list>

    public ${entity.className} toEntity() {
        ${entity.className} entity = new ${entity.className}();
        <#list fieldInfoList as field>
        <#if field.showInImport>
        entity.set${field.fieldName?cap_first}(${field.fieldName});
        </#if>
        </#list>
        return entity;
    }
}
