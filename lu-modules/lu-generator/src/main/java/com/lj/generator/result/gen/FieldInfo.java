package com.lj.generator.result.gen;

import com.lj.common.utils.ClassUtils;
import com.lj.dict.result.EnumDictVo;
import com.lj.generator.entity.GenColumnConfig;
import com.lj.generator.utils.GenUtils;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * @author luojing
 * @since 2024/11/20 15:57
 */
@Data
@Accessors(chain = true)
public class FieldInfo {

    /**
     * 该字段存在于父类中，则实体不会生成该字段
     */
    private boolean existSuperClass;

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 字段类型
     */
    private String fieldType;

    /**
     * 字段可能需要引入的依赖
     * 例如: java.time.LocalDateTime
     */
    private String importType;

    /**
     * 枚举字典,如果该字段不为空那么fieldType失效
     */
    private EnumDictVo enumDict;

    /**
     * 枚举字段字典类型 同enumDict
     */
    private String enumDictFieldType;

    /**
     * 数据库列名
     */
    private String columnName;

    /**
     * 注释
     */
    private String comment;

    /**
     * 是否需要@TableField 主键因为可能字段名与列名不匹配了
     */
    private boolean convert;

    /**
     * 是否是主键 添加 @TableId 注解
     */
    private boolean pk;

    private Boolean required;

    private Boolean showInList;

    private Boolean showInQuery;

    private Integer queryType;

    private Boolean showInInfo;

    private Boolean showInSave;

    private Boolean showInUpdate;

    private Integer formType;

    private Boolean showInImport;

    private Boolean showInExport;

    public FieldInfo(Set<String> superClassColumn, GenColumnConfig columnConfig) {
        this.existSuperClass = superClassColumn.contains(columnConfig.getColumnName());
        this.fieldName = columnConfig.getFieldName();
        this.fieldType = ClassUtils.getClassSimpleName(columnConfig.getFieldType());
        this.columnName = columnConfig.getColumnName();
        this.comment = columnConfig.getComment();
        this.convert = !GenUtils.fieldEqualityColumn(columnConfig.getFieldName(), columnConfig.getColumnName());
        this.pk = columnConfig.getColumnPk();
        this.required = columnConfig.getRequired();
        this.showInList = columnConfig.getShowInList();
        this.showInQuery = columnConfig.getShowInQuery();
        this.queryType = columnConfig.getQueryType();
        this.showInInfo = columnConfig.getShowInInfo();
        this.showInSave = columnConfig.getShowInSave();
        this.showInUpdate = columnConfig.getShowInUpdate();
        this.formType = columnConfig.getFormType();
        if (!columnConfig.getFieldType().equals(fieldType)) {
            // 说明不是lang包下的类型，需要引入
            this.importType = columnConfig.getFieldType();
        }
        this.showInImport = columnConfig.getShowInImport();
        this.showInExport = columnConfig.getShowInExport();
    }
}
