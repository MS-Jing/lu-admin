package com.lj.generator.result.gen;

import lombok.Data;

/**
 * @author luojing
 * @since 2024/11/20 15:57
 */
@Data
public class FieldInfo {

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 字段类型
     */
    private String fieldType;

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
}
