package com.lj.generator.entity.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author luojing
 * @since 2024/11/12 18:07
 */
@Builder
@Data
public class ColumnInfoResult {

    /**
     * 主键列
     */
    private Boolean columnPk;

    /**
     * 列名
     */
    private String columnName;

    /**
     * 列类型名称
     */
    private String columnTypeName;

    /**
     * 大小或数据长度
     */
    private Integer columnSize;
    private Integer columnDigit;

    /**
     * 字段名称
     */
    private String fieldName;


    /**
     * 字段类型
     */
    private String fieldType;

    /**
     * 注释
     */
    private String comment;

    /**
     * 不能为空的列
     */
    private Boolean required;
}
