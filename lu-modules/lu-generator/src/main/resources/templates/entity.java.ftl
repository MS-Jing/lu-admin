package ${packageInfo.entity};

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
<#list packageInfo.entityPackages as pkg>
import ${pkg};
</#list>

/**
 * <p>
 * ${tableInfo.comment!}
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("${tableInfo.name}")
public class ${entityName} extends ${superEntityClass} {

    private static final long serialVersionUID = 1L;
<#list fieldInfoList as field>

    /**
     * ${field.comment}
     */
    <#if field.pk>
    @TableId(value = "${field.columnName}", type = IdType.ASSIGN_ID)
    </#if>
    <#if field.convert>
    @TableField("${field.columnName}")
    </#if>
    private ${field.fieldType} ${field.fieldName};
</#list>
}
