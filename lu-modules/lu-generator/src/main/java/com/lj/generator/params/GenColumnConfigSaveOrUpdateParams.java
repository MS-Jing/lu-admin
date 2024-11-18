package com.lj.generator.params;

import com.lj.generator.entity.GenColumnConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author luojing
 * @since 2024/11/18 18:24
 */
@Data
public class GenColumnConfigSaveOrUpdateParams {

    @Schema(description = "更新的时候必填")
    private Long id;
    @Schema(description = "列是否是主键")
    private Boolean columnPk;

    @Schema(description = "列名")
    private String columnName;

    @Schema(description = "列类型名称")
    private String columnTypeName;

    @Schema(description = "列大小")
    private Integer columnSize;
    @Schema(description = "列位数")
    private Integer columnDigit;

    @Schema(description = "字段名")
    private String fieldName;

    @Schema(description = "字段的类型")
    private String fieldType;

    /**
     * 账号类型可以下拉，然后选择对应类型的字典
     */
    @Schema(description = "枚举字典类型 优先级比fieldType高")
    private String enumDictType;

    @Schema(description = "字段注释")
    private String comment;

    @Schema(description = "字段是否必填")
    private Boolean required;

    @Schema(description = "字段是否出现在分页列表中")
    private Boolean showInList;

    @Schema(description = "字段是否出现在分页查询中")
    private Boolean showInQuery;

    @Schema(description = "查询的类型 showInQuery为true生效")
    private Integer queryType;

    @Schema(description = "字段是否出现在新增接口中")
    private Boolean showInSave;

    @Schema(description = "字段是否出现在更新接口中")
    private Boolean showInUpdate;

    @Schema(description = "表单的类型 showInSave和showInUpdate有一个为true生效")
    private Integer formType;

    public GenColumnConfig toEntity() {
        GenColumnConfig genColumnConfig = new GenColumnConfig();
        genColumnConfig.setId(id);
        genColumnConfig.setColumnPk(columnPk);
        genColumnConfig.setColumnName(columnName);
        genColumnConfig.setColumnTypeName(columnTypeName);
        genColumnConfig.setColumnSize(columnSize);
        genColumnConfig.setColumnDigit(columnDigit);
        genColumnConfig.setFieldName(fieldName);
        genColumnConfig.setFieldType(fieldType);
        genColumnConfig.setEnumDictType(enumDictType);
        genColumnConfig.setComment(comment);
        genColumnConfig.setRequired(required);
        genColumnConfig.setShowInList(showInList);
        genColumnConfig.setShowInQuery(showInQuery);
        genColumnConfig.setQueryType(queryType);
        genColumnConfig.setShowInSave(showInSave);
        genColumnConfig.setShowInUpdate(showInUpdate);
        genColumnConfig.setFormType(formType);
        return genColumnConfig;
    }
}
