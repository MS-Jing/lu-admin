package com.lj.dict.result;

import lombok.Data;

/**
 * @author luojing
 * @date 2024/11/16
 */
@Data
public class DictItem<T> {
    /**
     * 字典值
     */
    private T value;

    /**
     * 字典描述
     */
    private String label;
}
