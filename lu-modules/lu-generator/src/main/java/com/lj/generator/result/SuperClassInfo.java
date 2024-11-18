package com.lj.generator.result;

import lombok.Data;

import java.util.List;

/**
 * @author luojing
 * @date 2024/11/16
 * 可选父类的信息
 */
@Data
public class SuperClassInfo {
    /**
     * 实体名称
     */
    private String name;
    /**
     * 字段信息
     */
    private List<SuperClassFieldInfo> fieldInfoList;
}
