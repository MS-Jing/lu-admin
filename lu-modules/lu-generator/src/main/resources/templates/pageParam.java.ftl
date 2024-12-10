package ${pageParam.packagePath};

import com.lj.mp.utils.AbstractPageQueryParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
<#list pageParam.packages as pkg>
import ${pkg};
</#list>

/**
 * <p>
 * ${tableComment!} 分页查询参数
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@EqualsAndHashCode(callSuper = true)
<#if tableComment??>@Schema(description = "${tableComment} 分页查询参数")</#if>
public class ${pageParam.className} extends AbstractPageQueryParams {
<#list fieldInfoList as field>
    <#if field.showInQuery>

    <#if field.comment!?length gt 0>
    @Schema(description = "${field.comment}<#if field.enumDict??> 参考字典: ${field.enumDict.name}</#if>")
    <#elseif field.enumDict??>
    @Schema(description = "参考字典: ${field.enumDict.name}")
    </#if>
    private ${field.fieldType} ${field.fieldName};
    </#if>
</#list>
}
