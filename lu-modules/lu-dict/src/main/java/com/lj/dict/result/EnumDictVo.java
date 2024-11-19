package com.lj.dict.result;

import lombok.Data;

import java.util.List;

/**
 * @author luojing
 * @date 2024/11/16
 */
@Data
public class EnumDictVo {

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典类名
     */
    private String className;

    /**
     * 字典描述
     */
    private String description;

    /**
     * 是否是框架定义的标准枚举
     */
    private Boolean standard;

    /**
     * 字典值类型
     */
    private String valueType;

    /**
     * 字典项
     */
    private List<EnumDictItem<Object>> dictItemList;

}
