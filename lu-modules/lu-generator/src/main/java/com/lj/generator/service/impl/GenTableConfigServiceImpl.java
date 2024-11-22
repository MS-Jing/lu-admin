package com.lj.generator.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.common.exception.CommonException;
import com.lj.common.utils.CheckUtils;
import com.lj.common.utils.ClassUtils;
import com.lj.dict.params.DictQueryParams;
import com.lj.dict.result.EnumDictVo;
import com.lj.dict.service.EnumDictService;
import com.lj.generator.constant.GenConstant;
import com.lj.generator.engine.TemplateEngine;
import com.lj.generator.entity.GenColumnConfig;
import com.lj.generator.entity.GenTableConfig;
import com.lj.generator.mapper.GenTableConfigMapper;
import com.lj.generator.params.GenColumnConfigSaveOrUpdateParams;
import com.lj.generator.params.GenTableConfigPageParams;
import com.lj.generator.params.GenTableConfigSaveOrUpdateParams;
import com.lj.generator.result.*;
import com.lj.generator.result.gen.*;
import com.lj.generator.service.GenColumnConfigService;
import com.lj.generator.service.GenTableConfigService;
import com.lj.generator.utils.GenUtils;
import com.lj.generator.utils.TypeMapper;
import com.lj.mp.standard.StandardEntity;
import com.lj.mp.standard.StandardServiceImpl;
import com.lj.mp.utils.PageQueryUtils;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 生成器表配置 服务实现类
 * </p>
 *
 * @author lj
 * @since 2024-11-12
 */
@Service
@Slf4j
public class GenTableConfigServiceImpl extends StandardServiceImpl<GenTableConfigMapper, GenTableConfig> implements GenTableConfigService, InitializingBean {

    @Resource
    private DataSource dataSource;
    @Resource
    private GenColumnConfigService columnConfigService;
    @Resource
    private EnumDictService enumDictService;
    @Resource
    private TemplateEngine templateEngine;

    @Override
    public List<String> enableGenTable() {
        // 所有的物理表
        List<String> tableList = MetaUtil.getTables(dataSource);
        // 查询所有已生成的表
        List<String> configTableList = this.listObjs(this.lambdaQueryWrapper().select(GenTableConfig::getTableName), Object::toString);
        // 没有配置的表名
        return CollUtil.subtractToList(tableList, configTableList);
    }

    @Override
    public TableInfoResult loadTableInfo(String tableName) {
        CheckUtils.ifBlank(tableName, "请输入正确的表名");

        Table tableMeta = MetaUtil.getTableMeta(dataSource, tableName);
        Collection<Column> columns = tableMeta.getColumns();

        CheckUtils.ifEmpty(columns, "请输入正确的表名");
        // 转换内容
        List<ColumnInfoResult> columnInfoList = new ArrayList<>();
        for (Column column : columns) {
            ColumnInfoResult columnInfo = ColumnInfoResult.builder()
                    .columnPk(column.isPk())
                    .columnName(column.getName())
                    .columnTypeName(column.getTypeName())
                    .columnSize((int) column.getSize())
                    .columnDigit(column.getDigit())
                    .fieldName(GenUtils.columnNameToFieldName(column.getName()))
                    .fieldType(TypeMapper.getJavaType(column.getType(), (int) column.getSize()))
                    .comment(column.getComment())
                    // 主键不是非必须的
                    .required(!column.isPk() && !column.isNullable())
                    .build();
            columnInfoList.add(columnInfo);
        }
        String[] underline = tableName.split(StrPool.UNDERLINE);
        return TableInfoResult.builder()
                .tableName(tableMeta.getTableName())
                .comment(tableMeta.getComment())
                .tablePrefix(underline.length >= 2 ? underline[0] : "")
                .columnInfoList(columnInfoList)
                .build();
    }

    private final List<SuperClassInfo> superClassList = new ArrayList<>();


    @Override
    public List<String> optionalSuperClass() {
        return superClassList.stream().map(SuperClassInfo::getName).collect(Collectors.toList());
    }

    private SuperClassInfo getSuperClassInfoByName(String superClass) {
        return superClassList.stream().filter(superClassInfo -> superClassInfo.getName().equals(superClass)).findFirst().orElseThrow(() -> new CommonException("父类不存在？"));
    }

    @Override
    public IPage<GenTableConfigPageResult> pageQuery(GenTableConfigPageParams pageParams) {
        return this.page(PageQueryUtils.getPage(pageParams), lambdaQueryWrapper()
                        .eq(StrUtil.isNotBlank(pageParams.getTableName()), GenTableConfig::getTableName, pageParams.getTableName()))
                .convert(GenTableConfigPageResult::of);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(GenTableConfigSaveOrUpdateParams params) {
        verifySaveOrUpdateParams(params);
        // 保存表配置
        GenTableConfig entity = params.toEntity();
        if (entity.getId() != null) {
            this.updateById(entity);
        } else {
            this.save(entity);
        }
        // 保存列配置
        List<GenColumnConfig> genColumnConfigList = params.getColumnConfigList().stream().map(GenColumnConfigSaveOrUpdateParams::toEntity).toList();
        // 字段排序
        int sort = 1;
        for (GenColumnConfig genColumnConfig : genColumnConfigList) {
            genColumnConfig.setTableId(entity.getId());
            genColumnConfig.setFieldSort(sort++);
        }
        // 将新增和更新的分开
        List<GenColumnConfig> updateList = genColumnConfigList.stream().filter(c -> c.getId() != null).toList();
        List<GenColumnConfig> saveList = genColumnConfigList.stream().filter(c -> c.getId() == null).toList();
        // 不用判空mp会判空的
        columnConfigService.updateBatchById(updateList);
        columnConfigService.saveBatch(saveList);
    }

    private void verifySaveOrUpdateParams(GenTableConfigSaveOrUpdateParams params) {
        // 父类字段
        String superClass = params.getSuperClass();
        // 父类列名与字段信息映射
        Map<String, SuperClassFieldInfo> superClassColumnNameInfoMap = superClassList.stream().filter(sc -> sc.getName().equals(superClass))
                .map(SuperClassInfo::getFieldInfoList)
                .findFirst()
                .orElseThrow(() -> new CommonException("不支持的父类:" + superClass))
                .stream().collect(Collectors.toMap(SuperClassFieldInfo::getColumnName, Function.identity()));
        // 物理表信息
        TableInfoResult tableInfoResult = loadTableInfo(params.getTableName());
        // 校验父类与要生成的表还有物理表的匹配
        Map<String, GenColumnConfigSaveOrUpdateParams> columnNameColumnConfig = params.getColumnConfigList().stream().collect(Collectors.toMap(GenColumnConfigSaveOrUpdateParams::getColumnName, Function.identity()));
        Set<String> temp = new HashSet<>();
        List<ColumnInfoResult> columnInfoList = tableInfoResult.getColumnInfoList();
        for (ColumnInfoResult columnInfoResult : columnInfoList) {
            String columnName = columnInfoResult.getColumnName();
            GenColumnConfigSaveOrUpdateParams columnParams = columnNameColumnConfig.get(columnName);
            if (columnParams == null) {
                // 要生成的实体必须要有物理表的所有字段
                throw new CommonException("缺少列: " + columnInfoResult.getColumnName() + " 配置");
            }
            String fieldType = columnInfoResult.getFieldType();
            // 字典枚举类型去字段类型优先级高
            String enumDictType = columnParams.getEnumDictType();
            EnumDictVo enumDict = null;
            if (StrUtil.isNotBlank(enumDictType)) {
                enumDict = getEnumDictVo(enumDictType, fieldType);
            } else if (!fieldType.equals(columnParams.getFieldType())) {
                throw new CommonException("字段与列类型不匹配 " + columnParams.getFieldType() + "->" + fieldType);
            }
            // 判断父类
            SuperClassFieldInfo superClassFieldInfo = superClassColumnNameInfoMap.get(columnName);
            if (superClassFieldInfo == null) {
                continue;
            }
            // 父类的类型可能并不与数据库的类型一样(可能是枚举)
            if (enumDict != null) {
                if (!enumDict.getClassName().equals(superClassFieldInfo.getFieldType())) {
                    throw new CommonException("列: " + columnName + " 在父类中已出现,他并不是字典" + enumDictType + "类型");
                }
            } else if (!columnParams.getFieldType().equals(superClassFieldInfo.getFieldType())) {
                throw new CommonException("列: " + columnName + " 在父类中已出现,类型不匹配 " + columnParams.getFieldType() + "->" + superClassFieldInfo.getFieldType());
            }
            temp.add(columnName);
        }
        List<String> dissatisfyFieldName = CollUtil.subtractToList(superClassColumnNameInfoMap.keySet(), temp);
        if (CollUtil.isNotEmpty(dissatisfyFieldName)) {
            throw new CommonException("无法将分配父类,缺少字段:" + dissatisfyFieldName);
        }
    }

    private EnumDictVo getEnumDictVo(String enumDictType, String fieldType) {
        List<EnumDictVo> dict = enumDictService.getDict(DictQueryParams.builder()
                .name(enumDictType)
                // 一定要是mp标准的枚举 （实现了IEnum接口）
                .standard(true)
                // 字段类型一定要匹配啊 不能说这个字段我要个int类型，但是你却给我一个string类型的枚举吧
                .valueType(fieldType)
                .build());
        if (CollUtil.isEmpty(dict)) {
            throw new CommonException("字典不存在或者类型不匹配:" + enumDictType);
        }
        return dict.get(0);
    }

    @Override
    public List<GenPreviewResult> preview(Long tableId) {
        GenTableConfig tableConfig = getById(tableId);
        CheckUtils.ifNull(tableConfig, "表不存在！");
        GenTemplateInfo genTemplateInfo = buildGenTemplateInfo(tableConfig);
        // 预览实体表
//        String preview = templateEngine.preview(genTemplateInfo, GenConstant.entityTemplate);
//        String preview = templateEngine.preview(genTemplateInfo, GenConstant.pageParamTemplate);
//        String preview = templateEngine.preview(genTemplateInfo, GenConstant.pageResultTemplate);
//        String preview = templateEngine.preview(genTemplateInfo, GenConstant.infoResultTemplate);
//        String preview = templateEngine.preview(genTemplateInfo, GenConstant.saveParamTemplate);
//        String preview = templateEngine.preview(genTemplateInfo, GenConstant.updateParamTemplate);
//        String preview = templateEngine.preview(genTemplateInfo, GenConstant.serviceTemplate);
        String preview = templateEngine.preview(genTemplateInfo, GenConstant.serviceImplTemplate);
//        String preview = templateEngine.preview(genTemplateInfo, GenConstant.mapperTemplate);
        System.out.println(preview);

        return null;
    }

    private GenTemplateInfo buildGenTemplateInfo(GenTableConfig tableConfig) {
        String basePackageName = tableConfig.getPackageName() + StrPool.DOT + tableConfig.getModuleName();
        SuperClassInfo superClassInfo = getSuperClassInfoByName(tableConfig.getSuperClass());
        // 判断字段
        List<FieldInfo> fieldInfos = buildFieldInfoList(tableConfig, superClassInfo);
        GenTemplateInfo genTemplateInfo = new GenTemplateInfo()
                .setTableName(tableConfig.getTableName())
                .setTableComment(tableConfig.getComment())
                .setModuleName(tableConfig.getModuleName())
                .setAuthor(tableConfig.getAuthor())
                .setDate(LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"))
                .setFieldInfoList(fieldInfos)
                .setPkType(fieldInfos.stream()
                        .filter(FieldInfo::isPk)
                        .map(FieldInfo::getFieldType)
                        .findFirst().orElseThrow(() -> new CommonException("表不存在主键?")))
                .setGenPage(tableConfig.getGenPage())
                .setGenInfo(tableConfig.getGenInfo())
                .setGenSave(tableConfig.getGenSave())
                .setGenUpdate(tableConfig.getGenUpdate())
                .setGenDeleted(tableConfig.getGenDeleted());
        genTemplateInfo.setEntity(buildEntityInfo(genTemplateInfo, tableConfig, basePackageName, superClassInfo));
        genTemplateInfo.setPageParam(buildPageParamInfo(genTemplateInfo, tableConfig, basePackageName));
        genTemplateInfo.setPageResult(buildPageResultInfo(genTemplateInfo, tableConfig, basePackageName));
        genTemplateInfo.setInfoResult(buildInfoResultInfo(genTemplateInfo, tableConfig, basePackageName));
        genTemplateInfo.setSaveParam(buildSaveParamInfo(genTemplateInfo, tableConfig, basePackageName));
        genTemplateInfo.setUpdateParam(buildUpdateParamInfo(genTemplateInfo, tableConfig, basePackageName));
        genTemplateInfo.setService(buildServiceInfo(genTemplateInfo, tableConfig, basePackageName));
        genTemplateInfo.setServiceImpl(buildServiceImplInfo(genTemplateInfo, tableConfig, basePackageName));
        genTemplateInfo.setMapper(buildMapperInfo(genTemplateInfo, tableConfig, basePackageName));
        return genTemplateInfo;
    }

    private MapperInfo buildMapperInfo(GenTemplateInfo genTemplateInfo, GenTableConfig tableConfig, String basePackageName) {
        Set<String> packages = new HashSet<>();
        MapperInfo mapperInfo = new MapperInfo();
        String packagePath = basePackageName + StrPool.DOT + GenConstant.mapperPackageName;
        mapperInfo.setPackagePath(packagePath);
        mapperInfo.setPackages(packages);
        String className = genTemplateInfo.getEntity().getClassName() + GenConstant.mapperSuffix;
        mapperInfo.setClassName(className);
        mapperInfo.setFileName(className + GenConstant.javaFileSuffix);
        // 文件路径
        mapperInfo.setFilePath(StrUtil.join(FileUtil.FILE_SEPARATOR, tableConfig.getModuleName(), GenConstant.javaDir, packagePath.replace(StrPool.DOT, FileUtil.FILE_SEPARATOR)));
        return mapperInfo;
    }

    private ServiceInfo buildServiceInfo(GenTemplateInfo genTemplateInfo, GenTableConfig tableConfig, String basePackageName) {
        Set<String> packages = new HashSet<>();
        if (genTemplateInfo.getGenPage()) {
            packages.add(ClassUtil.getClassName(IPage.class, false));
            packages.add(genTemplateInfo.getPageParam().getClassPath());
            packages.add(genTemplateInfo.getPageResult().getClassPath());
        }
        if (genTemplateInfo.getGenInfo()) {
            packages.add(genTemplateInfo.getInfoResult().getClassPath());
        }
        if (genTemplateInfo.getGenSave()) {
            packages.add(genTemplateInfo.getSaveParam().getClassPath());
        }
        if (genTemplateInfo.getGenSave()) {
            packages.add(genTemplateInfo.getUpdateParam().getClassPath());
        }
        ServiceInfo serviceInfo = new ServiceInfo();
        String packagePath = basePackageName + StrPool.DOT + GenConstant.servicePackageName;
        serviceInfo.setPackagePath(packagePath);
        serviceInfo.setPackages(packages);
        String className = genTemplateInfo.getEntity().getClassName() + GenConstant.serviceSuffix;
        serviceInfo.setClassName(className);
        serviceInfo.setFileName(className + GenConstant.javaFileSuffix);
        // 文件路径
        serviceInfo.setFilePath(StrUtil.join(FileUtil.FILE_SEPARATOR, tableConfig.getModuleName(), GenConstant.javaDir, packagePath.replace(StrPool.DOT, FileUtil.FILE_SEPARATOR)));
        return serviceInfo;
    }

    private ServiceImplInfo buildServiceImplInfo(GenTemplateInfo genTemplateInfo, GenTableConfig tableConfig, String basePackageName) {
        Set<String> packages = new HashSet<>();
        ServiceImplInfo serviceImplInfo = new ServiceImplInfo();
        String packagePath = basePackageName + StrPool.DOT + GenConstant.servicePackageName + StrPool.DOT + GenConstant.serviceImplPackageName;
        serviceImplInfo.setPackagePath(packagePath);
        serviceImplInfo.setPackages(packages);
        String className = genTemplateInfo.getEntity().getClassName() + GenConstant.serviceImplSuffix;
        serviceImplInfo.setClassName(className);
        serviceImplInfo.setFileName(className + GenConstant.javaFileSuffix);
        // 文件路径
        serviceImplInfo.setFilePath(StrUtil.join(FileUtil.FILE_SEPARATOR, tableConfig.getModuleName(), GenConstant.javaDir, packagePath.replace(StrPool.DOT, FileUtil.FILE_SEPARATOR)));
        return serviceImplInfo;
    }

    public List<FieldInfo> buildFieldInfoList(GenTableConfig tableConfig, SuperClassInfo superClassInfo) {
        List<GenColumnConfig> columnConfigList = columnConfigService.getByTableId(tableConfig.getId());
        CheckUtils.ifEmpty(columnConfigList, "列配置为空？");
        // 父类对应的列
        Set<String> superClassColumn = superClassInfo.getFieldInfoList().stream().map(SuperClassFieldInfo::getColumnName).collect(Collectors.toSet());
        List<FieldInfo> fieldInfos = new ArrayList<>();
        for (GenColumnConfig columnConfig : columnConfigList) {
            FieldInfo fieldInfo = new FieldInfo()
                    .setExistSuperClass(superClassColumn.contains(columnConfig.getColumnName()))
                    .setFieldName(columnConfig.getFieldName())
                    .setFieldType(ClassUtils.getClassSimpleName(columnConfig.getFieldType()))
                    .setColumnName(columnConfig.getColumnName())
                    .setComment(columnConfig.getComment())
                    .setConvert(!GenUtils.fieldEqualityColumn(columnConfig.getFieldName(), columnConfig.getColumnName()))
                    .setPk(columnConfig.getColumnPk())
                    .setRequired(columnConfig.getRequired())
                    .setShowInList(columnConfig.getShowInList())
                    .setShowInQuery(columnConfig.getShowInQuery())
                    .setQueryType(columnConfig.getQueryType())
                    .setShowInInfo(columnConfig.getShowInInfo())
                    .setShowInSave(columnConfig.getShowInSave())
                    .setShowInUpdate(columnConfig.getShowInUpdate())
                    .setFormType(columnConfig.getFormType());
            if (!columnConfig.getFieldType().equals(fieldInfo.getFieldType())) {
                // 说明不是lang包下的类型，需要引入
                fieldInfo.setImportType(columnConfig.getFieldType());
            }
            if (StrUtil.isNotBlank(columnConfig.getEnumDictType())) {
                // 说明是字典
                EnumDictVo enumDictVo = getEnumDictVo(columnConfig.getEnumDictType(), columnConfig.getFieldType());
                fieldInfo.setEnumDict(enumDictVo);
                fieldInfo.setEnumDictFieldType(ClassUtils.getClassSimpleName(enumDictVo.getClassName()));
            }
            fieldInfos.add(fieldInfo);
        }
        return fieldInfos;
    }

    private PageParamInfo buildPageParamInfo(GenTemplateInfo genTemplateInfo, GenTableConfig tableConfig, String basePackageName) {
        Set<String> packages = new HashSet<>();
        List<FieldInfo> fieldInfoList = genTemplateInfo.getFieldInfoList();
        for (FieldInfo fieldInfo : fieldInfoList) {
            if (!fieldInfo.getShowInQuery()) {
                // 不出现在查询参数的字段不用检查
                continue;
            }
            // 不要直接用字典接收值
            if (StrUtil.isNotBlank(fieldInfo.getImportType())) {
                // 原类型需要导入包
                packages.add(fieldInfo.getImportType());
            }
        }
        PageParamInfo pageParamInfo = new PageParamInfo();
        String packagePath = basePackageName + StrPool.DOT + GenConstant.paramPackageName;
        pageParamInfo.setPackagePath(packagePath);
        pageParamInfo.setPackages(packages);
        String className = genTemplateInfo.getEntity().getClassName() + GenConstant.pageParamSuffix;
        pageParamInfo.setClassName(className);
        pageParamInfo.setFileName(className + GenConstant.javaFileSuffix);
        // 文件路径
        pageParamInfo.setFilePath(StrUtil.join(FileUtil.FILE_SEPARATOR, tableConfig.getModuleName(), GenConstant.javaDir, packagePath.replace(StrPool.DOT, FileUtil.FILE_SEPARATOR)));
        return pageParamInfo;
    }


    private PageResultInfo buildPageResultInfo(GenTemplateInfo genTemplateInfo, GenTableConfig tableConfig, String basePackageName) {
        Set<String> packages = new HashSet<>();
        List<FieldInfo> fieldInfoList = genTemplateInfo.getFieldInfoList();
        for (FieldInfo fieldInfo : fieldInfoList) {
            if (!fieldInfo.getShowInList()) {
                // 不出现在查询结果的字段不用检查
                continue;
            }
            // 不要直接用字典返回值
            if (StrUtil.isNotBlank(fieldInfo.getImportType())) {
                // 原类型需要导入包
                packages.add(fieldInfo.getImportType());
            }
        }
        PageResultInfo pageResultInfo = new PageResultInfo();
        String packagePath = basePackageName + StrPool.DOT + GenConstant.resultPackageName;
        pageResultInfo.setPackagePath(packagePath);
        pageResultInfo.setPackages(packages);
        String className = genTemplateInfo.getEntity().getClassName() + GenConstant.pageResultSuffix;
        pageResultInfo.setClassName(className);
        pageResultInfo.setFileName(className + GenConstant.javaFileSuffix);
        pageResultInfo.setFilePath(StrUtil.join(FileUtil.FILE_SEPARATOR, tableConfig.getModuleName(), GenConstant.javaDir, packagePath.replace(StrPool.DOT, FileUtil.FILE_SEPARATOR)));
        return pageResultInfo;
    }


    private InfoResultInfo buildInfoResultInfo(GenTemplateInfo genTemplateInfo, GenTableConfig tableConfig, String basePackageName) {
        Set<String> packages = new HashSet<>();
        List<FieldInfo> fieldInfoList = genTemplateInfo.getFieldInfoList();
        for (FieldInfo fieldInfo : fieldInfoList) {
            if (!fieldInfo.getShowInInfo()) {
                // 不出现在查询结果的字段不用检查
                continue;
            }
            // 不要直接用字典返回值
            if (StrUtil.isNotBlank(fieldInfo.getImportType())) {
                // 原类型需要导入包
                packages.add(fieldInfo.getImportType());
            }
        }
        InfoResultInfo infoResultInfo = new InfoResultInfo();
        String packagePath = basePackageName + StrPool.DOT + GenConstant.resultPackageName;
        infoResultInfo.setPackagePath(packagePath);
        infoResultInfo.setPackages(packages);
        String className = genTemplateInfo.getEntity().getClassName() + GenConstant.infoResultSuffix;
        infoResultInfo.setClassName(className);
        infoResultInfo.setFileName(className + GenConstant.javaFileSuffix);
        infoResultInfo.setFilePath(StrUtil.join(FileUtil.FILE_SEPARATOR, tableConfig.getModuleName(), GenConstant.javaDir, packagePath.replace(StrPool.DOT, FileUtil.FILE_SEPARATOR)));
        return infoResultInfo;
    }


    private SaveParamInfo buildSaveParamInfo(GenTemplateInfo genTemplateInfo, GenTableConfig tableConfig, String basePackageName) {
        Set<String> packages = new HashSet<>();
        List<FieldInfo> fieldInfoList = genTemplateInfo.getFieldInfoList();
        for (FieldInfo fieldInfo : fieldInfoList) {
            if (!fieldInfo.getShowInSave()) {
                // 不出现在保存的字段不用检查
                continue;
            }
            if (fieldInfo.getRequired()) {
                // 必填字段需要校验
                if (ClassUtil.getClassName(String.class, true).equals(fieldInfo.getFieldType())) {
                    packages.add(ClassUtil.getClassName(NotBlank.class, false));
                } else {
                    packages.add(ClassUtil.getClassName(NotNull.class, false));
                }
            }
            if (fieldInfo.getEnumDict() != null) {
                // 字段需要转换成entity实体,所以需要把字典导入
                packages.add(fieldInfo.getEnumDict().getClassName());
            }
            if (StrUtil.isNotBlank(fieldInfo.getImportType())) {
                // 原类型需要导入包
                packages.add(fieldInfo.getImportType());
            }
        }
        SaveParamInfo saveParamInfo = new SaveParamInfo();
        String packagePath = basePackageName + StrPool.DOT + GenConstant.paramPackageName;
        saveParamInfo.setPackagePath(packagePath);
        saveParamInfo.setPackages(packages);
        String className = genTemplateInfo.getEntity().getClassName() + GenConstant.saveParamSuffix;
        saveParamInfo.setClassName(className);
        saveParamInfo.setFileName(className + GenConstant.javaFileSuffix);
        saveParamInfo.setFilePath(StrUtil.join(FileUtil.FILE_SEPARATOR, tableConfig.getModuleName(), GenConstant.javaDir, packagePath.replace(StrPool.DOT, FileUtil.FILE_SEPARATOR)));
        return saveParamInfo;
    }


    private UpdateParamInfo buildUpdateParamInfo(GenTemplateInfo genTemplateInfo, GenTableConfig tableConfig, String basePackageName) {
        Set<String> packages = new HashSet<>();
        List<FieldInfo> fieldInfoList = genTemplateInfo.getFieldInfoList();
        for (FieldInfo fieldInfo : fieldInfoList) {
            if (!fieldInfo.getShowInUpdate()) {
                // 不出现在保存的字段不用检查
                continue;
            }
            if (fieldInfo.getEnumDict() != null) {
                // 字段需要转换成entity实体,所以需要把字典导入
                packages.add(fieldInfo.getEnumDict().getClassName());
            }
            if (StrUtil.isNotBlank(fieldInfo.getImportType())) {
                // 原类型需要导入包
                packages.add(fieldInfo.getImportType());
            }
        }
        UpdateParamInfo updateParamInfo = new UpdateParamInfo();
        String packagePath = basePackageName + StrPool.DOT + GenConstant.paramPackageName;
        updateParamInfo.setPackagePath(packagePath);
        updateParamInfo.setPackages(packages);
        String className = genTemplateInfo.getEntity().getClassName() + GenConstant.updateParamSuffix;
        updateParamInfo.setClassName(className);
        updateParamInfo.setFileName(className + GenConstant.javaFileSuffix);
        updateParamInfo.setFilePath(StrUtil.join(FileUtil.FILE_SEPARATOR, tableConfig.getModuleName(), GenConstant.javaDir, packagePath.replace(StrPool.DOT, FileUtil.FILE_SEPARATOR)));
        return updateParamInfo;
    }

    private EntityInfo buildEntityInfo(GenTemplateInfo genTemplateInfo, GenTableConfig tableConfig, String basePackageName, SuperClassInfo superClassInfo) {
        Set<String> packages = new HashSet<>();
        packages.add(superClassInfo.getName());
        List<FieldInfo> fieldInfoList = genTemplateInfo.getFieldInfoList();
        for (FieldInfo fieldInfo : fieldInfoList) {
            if (fieldInfo.isExistSuperClass()) {
                // 在父类的字段无需导入包
                continue;
            }
            if (fieldInfo.isPk()) {
                // 主键id不在父类
                packages.add(ClassUtil.getClassName(TableId.class, false));
            }
            if (fieldInfo.isConvert()) {
                // 字段需要转换
                packages.add(ClassUtil.getClassName(TableField.class, false));
            }
            if (fieldInfo.getEnumDict() != null) {
                // 需要把字典导入，字典的优先级大于原类型
                packages.add(fieldInfo.getEnumDict().getClassName());
                continue;
            }
            if (StrUtil.isNotBlank(fieldInfo.getImportType())) {
                // 原类型需要导入包
                packages.add(fieldInfo.getImportType());
            }
        }
        EntityInfo entityInfo = new EntityInfo();
        entityInfo.setSuperEntityClass(ClassUtils.getClassSimpleName(superClassInfo.getName()));
        String packagePath = basePackageName + StrPool.DOT + GenConstant.entityPackageName;
        entityInfo.setPackagePath(packagePath);
        entityInfo.setPackages(packages);
        // 文件名
        String className = GenUtils.tableNameToClassName(tableConfig.getTableName(), Boolean.TRUE.equals(tableConfig.getUnprefix()) ? tableConfig.getTablePrefix() : "");
        entityInfo.setClassName(className);
        entityInfo.setFileName(className + GenConstant.javaFileSuffix);
        // 文件路径
        entityInfo.setFilePath(StrUtil.join(FileUtil.FILE_SEPARATOR, tableConfig.getModuleName(), GenConstant.javaDir, packagePath.replace(StrPool.DOT, FileUtil.FILE_SEPARATOR)));
        return entityInfo;
    }

    private void init() {
        // 这里防止多次反射父类进行一下缓存
        Map<Class<?>, List<SuperClassFieldInfo>> infoMap = new HashMap<>();
        // 先把规范的实体添加进去
        superClassList.add(toSuperClassInfo(StandardEntity.class, infoMap));
        // 扫描类路径下所有 实现StandardEntity的抽象类
        Set<Class<?>> superClassSet = ClassUtil.scanPackageBySuper("", StandardEntity.class);
        for (Class<?> superClass : superClassSet) {
            // 只有抽象类才能被作为父类
            if (!ClassUtil.isAbstract(superClass)) {
                continue;
            }
            superClassList.add(toSuperClassInfo(superClass, infoMap));
        }
    }

    private SuperClassInfo toSuperClassInfo(Class<?> superClass, Map<Class<?>, List<SuperClassFieldInfo>> infoMap) {
        SuperClassInfo superClassInfo = new SuperClassInfo();
        superClassInfo.setName(ClassUtil.getClassName(superClass, false));
        // 解析字段
        superClassInfo.setFieldInfoList(getFieldInfoList(superClass, infoMap));
        return superClassInfo;
    }

    private List<SuperClassFieldInfo> getFieldInfoList(Class<?> clazz, Map<Class<?>, List<SuperClassFieldInfo>> infoMap) {
        List<SuperClassFieldInfo> fieldInfoList = infoMap.get(clazz);
        if (fieldInfoList != null) {
            // 之前被自己的子类加载过，那么直接返回
            return fieldInfoList;
        }
        List<SuperClassFieldInfo> result = new ArrayList<>();
        // 将父类的字段加载进来
        Class<?> superClass = clazz.getSuperclass();
        if (clazz != StandardEntity.class) {
            List<SuperClassFieldInfo> superFieldInfoList = infoMap.get(superClass);
            // 父类已经被加载过了直接加进来
            // 父类还没有被加载过，先去加载父类的字段信息
            result.addAll(Optional.ofNullable(superFieldInfoList).orElseGet(() -> getFieldInfoList(superClass, infoMap)));
        }
        // 加载自己的
        Field[] declaredFields = ClassUtil.getDeclaredFields(clazz);
        for (Field declaredField : declaredFields) {
            SuperClassFieldInfo fieldInfo = new SuperClassFieldInfo();
            fieldInfo.setName(declaredField.getName());
            // 获取列名
            TableField tableField = declaredField.getAnnotation(TableField.class);
            if (tableField != null && StrUtil.isNotBlank(tableField.value())) {
                fieldInfo.setColumnName(tableField.value());
            } else {
                fieldInfo.setColumnName(GenUtils.fieldNameToColumnName(declaredField.getName()));
            }
            Class<?> fieldType = declaredField.getType();
            fieldInfo.setFieldType(ClassUtils.getClassName(fieldType));
            result.add(fieldInfo);
        }
        // 将自己的字段列表加入到缓存中
        infoMap.put(clazz, result);
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("加载可选父类...");
        long start = System.currentTimeMillis();
        init();
        log.info("加载可选父类加载完毕 {} ms", System.currentTimeMillis() - start);
    }
}
