package com.lj.generator.result;

import com.lj.generator.entity.GenColumnConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author luojing
 * @since 2024/12/5 17:41
 */
@Data
public class ColumnConfigInfoResult {

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

    @Schema(description = "字段是否出现在信息接口中")
    private Boolean showInInfo;

    @Schema(description = "字段是否出现在新增接口中")
    private Boolean showInSave;

    @Schema(description = "字段是否出现在更新接口中")
    private Boolean showInUpdate;

    @Schema(description = "表单的类型 showInSave和showInUpdate有一个为true生效")
    private Integer formType;

    @Schema(description = "字段是否出现在导入接口中")
    private Boolean showInImport;

    @Schema(description = "字段是否出现在导出接口中")
    private Boolean showInExport;

    public static ColumnConfigInfoResult of(GenColumnConfig entity) {
        if (entity == null) {
            return null;
        }
        ColumnConfigInfoResult result = new ColumnConfigInfoResult();
        result.setId(entity.getId());
        result.setColumnPk(entity.getColumnPk());
        result.setColumnName(entity.getColumnName());
        result.setColumnTypeName(entity.getColumnTypeName());
        result.setColumnSize(entity.getColumnSize());
        result.setColumnDigit(entity.getColumnDigit());
        result.setFieldName(entity.getFieldName());
        result.setFieldType(entity.getFieldType());
        result.setEnumDictType(entity.getEnumDictType());
        result.setComment(entity.getComment());
        result.setRequired(entity.getRequired());
        result.setShowInList(entity.getShowInList());
        result.setShowInQuery(entity.getShowInQuery());
        result.setQueryType(entity.getQueryType() == null ? null : entity.getQueryType().getValue());
        result.setShowInInfo(entity.getShowInInfo());
        result.setShowInSave(entity.getShowInSave());
        result.setShowInUpdate(entity.getShowInUpdate());
        result.setFormType(entity.getFormType());
        result.setShowInImport(entity.getShowInImport());
        result.setShowInExport(entity.getShowInExport());
        return result;
    }
}
