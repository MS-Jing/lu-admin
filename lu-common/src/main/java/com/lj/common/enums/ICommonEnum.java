package com.lj.common.enums;

/**
 * @author luojing
 * @date 2024/7/28
 * 通用枚举 枚举至少需要有这两个字段
 */
public interface ICommonEnum<T> {

    /**
     * 枚举对应的值
     */
    T getValue();

    /**
     * 枚举对应的描述
     */
    String getDesc();

}
