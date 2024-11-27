package ${saveParam.packagePath};

import ${entity.packagePath}.${entity.className};
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
<#list saveParam.packages as pkg>
import ${pkg};
</#list>

/**
 * <p>
 * ${tableComment!} 保存参数
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
<#if tableComment??>@Schema(description = "${tableComment} 保存参数")</#if>
public class ${saveParam.className} {
<#list fieldInfoList as field>
    <#if field.showInSave>

    <#if field.comment!?length gt 0>
    @Schema(description = "${field.comment} <#if field.enumDict??>参考字典: ${field.enumDict.name}</#if>")
    <#elseif field.enumDict??>
    @Schema(description = "参考字典: ${field.enumDict.name}")
    </#if>
    <#if field.required>
    <#if field.fieldType=="String">@NotBlank<#else>@NotNull</#if>(message = "<#if field.comment!?length gt 0>${field.comment}<#else>${field.fieldName}</#if> 不能为空")
    </#if>
    private ${field.fieldType} ${field.fieldName};
    </#if>
</#list>

    public ${entity.className} toEntity() {
        ${entity.className} entity = new ${entity.className}();
        <#list fieldInfoList as field>
        <#if field.showInSave>
        entity.set${field.fieldName?cap_first}(<#if field.enumDict??>${field.enumDictFieldType}.ofByValue(${field.fieldName})<#else>${field.fieldName}</#if>);
        </#if>
        </#list>
        return entity;
    }
}
