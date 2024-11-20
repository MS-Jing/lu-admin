package com.lj.generator.result.gen;

import lombok.Data;

import java.util.List;

/**
 * @author luojing
 * @since 2024/11/20 14:59
 * 生成器模板需要得信息
 */
@Data
public class GenTemplateInfo {
    private PackageInfo packageInfo;

    private TableInfo tableInfo;

    private List<FieldInfo> fieldInfoList;

    private String moduleName;

    /**
     * 实体的名称
     */
    private String entityName;

    /**
     * 实体的父类
     */
    private String superEntityClass;

    private String author;

    private String date;
}
