package ${infoResult.packagePath};

import ${entity.packagePath}.${entity.className};
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
<#list infoResult.packages as pkg>
import ${pkg};
</#list>

/**
 * <p>
 * ${tableComment!} 信息结果
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
<#if tableComment??>@Schema(description = "${tableComment} 信息结果")</#if>
public class ${infoResult.className} {
<#list fieldInfoList as field>
    <#if field.showInInfo>

    <#if field.comment!?length gt 0>
    @Schema(description = "${field.comment} <#if field.enumDict??>参考字典: ${field.enumDict.name}</#if>")
    <#elseif field.enumDict??>
    @Schema(description = "参考字典: ${field.enumDict.name}")
    </#if>
    private ${field.fieldType} ${field.fieldName};
    </#if>
</#list>

    public static ${infoResult.className} of(${entity.className} entity) {
        if (entity == null) {
            return null;
        }
        ${infoResult.className} result = new ${infoResult.className}();
        <#list fieldInfoList as field>
        <#if field.showInInfo>
        result.set${field.fieldName?cap_first}(entity.get${field.fieldName?cap_first}()<#if field.enumDict??>.getValue()</#if>);
        </#if>
        </#list>
        return result;
    }
}
