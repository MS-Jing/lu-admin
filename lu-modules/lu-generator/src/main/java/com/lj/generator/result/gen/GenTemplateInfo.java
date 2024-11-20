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

    /**
     * 真实的表名
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 字段信息
     */
    private List<FieldInfo> fieldInfoList;

    /**
     * 模块名
     */
    private String moduleName;

    private String author;

    private String date;

    /**
     * 实体相关
     */
    private EntityInfo entity;

    /**
     * mapper
     */
    private MapperInfo mapper;

    /**
     * service
     */
    private ServiceInfo service;

    /**
     * serviceImpl
     */
    private ServiceImplInfo serviceImpl;

    /**
     * controller
     */
    private ControllerInfo controller;
}
