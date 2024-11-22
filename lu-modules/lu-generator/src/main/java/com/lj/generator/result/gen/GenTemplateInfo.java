package com.lj.generator.result.gen;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author luojing
 * @since 2024/11/20 14:59
 * 生成器模板需要得信息
 */
@Data
@Accessors(chain = true)
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

    /**
     * 分页参数
     */
    private PageParamInfo pageParam;

    /**
     * 分页结果
     */
    private PageResultInfo pageResult;

    /**
     * 信息结果
     */
    private InfoResultInfo infoResult;

    /**
     * 保存参数
     */
    private SaveParamInfo saveParam;

    /**
     * 更新参数
     */
    private UpdateParamInfo updateParam;

}
