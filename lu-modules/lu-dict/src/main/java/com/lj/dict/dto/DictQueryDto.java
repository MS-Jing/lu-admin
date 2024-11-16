package com.lj.dict.dto;

import lombok.Data;

/**
 * @author luojing
 * @date 2024/11/16
 */
@Data
public class DictQueryDto {
    /**
     * 字典名称
     */
    private String name;

    /**
     * 是否是框架定义的标准枚举
     */
    private Boolean standard;

    /**
     * 字典值类型
     */
    private String valueType;
}
