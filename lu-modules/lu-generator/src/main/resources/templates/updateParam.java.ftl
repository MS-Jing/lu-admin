package ${updateParam.packagePath};

import ${entity.packagePath}.${entity.className};
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
<#list updateParam.packages as pkg>
import ${pkg};
</#list>

/**
 * <p>
 * ${tableComment!} 更新参数
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
<#if tableComment??>@Schema(description = "${tableComment} 更新参数")</#if>
public class ${updateParam.className} {
<#list fieldInfoList as field>
    <#if field.showInUpdate>

    <#if field.comment!?length gt 0>
    @Schema(description = "${field.comment} <#if field.enumDict??>参考字典: ${field.enumDict.name}</#if>")
    <#elseif field.enumDict??>
    @Schema(description = "参考字典: ${field.enumDict.name}")
    </#if>
    private ${field.fieldType} ${field.fieldName};
    </#if>
</#list>

    public ${entity.className} toEntity() {
        ${entity.className} entity = new ${entity.className}();
        <#list fieldInfoList as field>
        <#if field.showInUpdate>
        entity.set${field.fieldName?cap_first}(<#if field.enumDict??>${field.enumDict.simpleClassName}.ofByValue(${field.fieldName})<#else>${field.fieldName}</#if>);
        </#if>
        </#list>
        return entity;
    }
}
