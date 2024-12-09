package com.lj.generator.result.gen;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.lj.common.exception.CommonException;
import com.lj.generator.engine.TemplateEngine;
import com.lj.generator.entity.GenTableConfig;
import com.lj.generator.result.GenPreviewResult;
import com.lj.generator.result.SuperClassInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    /**
     * 包名 真实的包名应该是：packageName+"."+moduleName+具体的包名
     */
    private String packageName;

    private String tablePrefix;

    private Boolean unprefix;

    private String author;

    private String date;

    /**
     * 主键类型
     */
    private String pkType;

    /**
     * 是否生成分页接口
     */
    private Boolean genPage;

    /**
     * 是否生成信息接口
     */
    private Boolean genInfo;

    /**
     * 是否生成保存接口
     */
    private Boolean genSave;

    /**
     * 是否生成更新接口
     */
    private Boolean genUpdate;

    /**
     * 是否生成删除接口
     */
    private Boolean genDeleted;

    /**
     * 是否生成导入接口
     */
    private Boolean genImport;

    /**
     * 是否生成导出接口
     */
    private Boolean genExport;

    /**
     * 实体相关
     */
    private EntityInfo entity;

    /**
     * mapper
     */
    private MapperInfo mapper;

    /**
     * mapperXml
     */
    private MapperXmlInfo mapperXml;

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

    /**
     * 导入参数
     */
    private ImportParamInfo importParam;

    /**
     * 导出结果
     */
    private ExportResultInfo exportResult;

    public GenTemplateInfo(GenTableConfig tableConfig, List<FieldInfo> fieldInfos, SuperClassInfo superClassInfo) {
        this.tableName = tableConfig.getTableName();
        this.tableComment = tableConfig.getComment();
        this.moduleName = tableConfig.getModuleName();
        this.packageName = tableConfig.getPackageName();
        this.tablePrefix = tableConfig.getTablePrefix();
        this.unprefix = tableConfig.getUnprefix();
        this.author = tableConfig.getAuthor();
        this.date = LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
        this.fieldInfoList = fieldInfos;
        this.pkType = fieldInfos.stream()
                .filter(FieldInfo::isPk)
                .map(FieldInfo::getFieldType)
                .findFirst().orElseThrow(() -> new CommonException("表不存在主键?"));
        this.genPage = tableConfig.getGenPage();
        this.genInfo = tableConfig.getGenInfo();
        this.genSave = tableConfig.getGenSave();
        this.genUpdate = tableConfig.getGenUpdate();
        this.genDeleted = tableConfig.getGenDeleted();
        this.genImport = tableConfig.getGenImport();
        this.genExport = tableConfig.getGenExport();
        this.entity = new EntityInfo(this, superClassInfo);
        this.pageParam = new PageParamInfo(this);
        this.pageResult = new PageResultInfo(this);
        this.infoResult = new InfoResultInfo(this);
        this.saveParam = new SaveParamInfo(this);
        this.updateParam = new UpdateParamInfo(this);
        this.importParam = new ImportParamInfo(this);
        this.exportResult = new ExportResultInfo(this);
        this.service = new ServiceInfo(this);
        this.serviceImpl = new ServiceImplInfo(this);
        this.mapper = new MapperInfo(this);
        this.mapperXml = new MapperXmlInfo(this);
        this.controller = new ControllerInfo(this);
    }

    public List<GenPreviewResult> preview(TemplateEngine templateEngine) {
        List<GenPreviewResult> previewResultList = new ArrayList<>();
        previewResultList.add(entity.preview(this, templateEngine));
        previewResultList.add(mapper.preview(this, templateEngine));
        previewResultList.add(mapperXml.preview(this, templateEngine));
        previewResultList.add(serviceImpl.preview(this, templateEngine));
        previewResultList.add(service.preview(this, templateEngine));
        previewResultList.add(controller.preview(this, templateEngine));
        if (genPage) {
            previewResultList.add(pageParam.preview(this, templateEngine));
            previewResultList.add(pageResult.preview(this, templateEngine));
        }
        if (genInfo) {
            previewResultList.add(infoResult.preview(this, templateEngine));
        }
        if (genSave) {
            previewResultList.add(saveParam.preview(this, templateEngine));
        }
        if (genUpdate) {
            previewResultList.add(updateParam.preview(this, templateEngine));
        }
        if (genImport) {
            previewResultList.add(importParam.preview(this, templateEngine));
        }
        if (genExport) {
            previewResultList.add(exportResult.preview(this, templateEngine));
        }
        return previewResultList;
    }

    public void generate(TemplateEngine templateEngine, String tempDir) {
        entity.generate(this, templateEngine, tempDir);
        mapper.generate(this, templateEngine, tempDir);
        mapperXml.generate(this, templateEngine, tempDir);
        serviceImpl.generate(this, templateEngine, tempDir);
        service.generate(this, templateEngine, tempDir);
        controller.generate(this, templateEngine, tempDir);
        if (genPage) {
            pageParam.generate(this, templateEngine, tempDir);
            pageResult.generate(this, templateEngine, tempDir);
        }
        if (genInfo) {
            infoResult.generate(this, templateEngine, tempDir);
        }
        if (genSave) {
            saveParam.generate(this, templateEngine, tempDir);
        }
        if (genUpdate) {
            updateParam.generate(this, templateEngine, tempDir);
        }
        if (genImport) {
            importParam.generate(this, templateEngine, tempDir);
        }
        if (genExport) {
            exportResult.generate(this, templateEngine, tempDir);
        }
    }
}
