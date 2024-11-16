package com.lj.dict.vo;

import lombok.Data;

/**
 * @author luojing
 * @date 2024/11/16
 */
@Data
public class EnumDictItem<T> {
    /**
     * 字典值
     */
    private T itemValue;

    /**
     * 字典描述
     */
    private String description;
}
