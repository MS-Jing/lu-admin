package com.lj.generator.result;

import lombok.Data;

/**
 * @author luojing
 * @date 2024/11/16
 */
@Data
public class SuperClassFieldInfo {
    /**
     * 字段名称
     */
    private String name;

    /**
     * 对应的列名称
     */
    private String columnName;

    /**
     * 字段类型
     */
    private String fieldType;
}
