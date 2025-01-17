package ${entity.packagePath};

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
<#list entity.packages as pkg>
import ${pkg};
</#list>

/**
 * <p>
 * ${tableComment!}
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("${tableName}")
public class ${entity.className} extends ${entity.superEntityClass} {

    private static final long serialVersionUID = 1L;
<#list fieldInfoList as field>
    <#if !field.existSuperClass>

    <#if field.comment!?length gt 0>
    /**
     * ${field.comment}
     */
    </#if>
    <#if field.pk>
    @TableId(value = "${field.columnName}", type = IdType.ASSIGN_ID)
    </#if>
    <#if field.convert>
    @TableField("${field.columnName}")
    </#if>
    private <#if field.enumDict??>${field.enumDict.simpleClassName}<#else>${field.fieldType}</#if> ${field.fieldName};
    </#if>
</#list>
}
