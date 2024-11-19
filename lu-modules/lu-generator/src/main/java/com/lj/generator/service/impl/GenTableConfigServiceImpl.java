package com.lj.generator.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.common.exception.CommonException;
import com.lj.common.utils.CheckUtils;
import com.lj.common.utils.ClassUtils;
import com.lj.dict.params.DictQueryParams;
import com.lj.dict.result.EnumDictVo;
import com.lj.dict.service.EnumDictService;
import com.lj.generator.entity.GenColumnConfig;
import com.lj.generator.entity.GenTableConfig;
import com.lj.generator.mapper.GenTableConfigMapper;
import com.lj.generator.params.GenColumnConfigSaveOrUpdateParams;
import com.lj.generator.params.GenTableConfigPageParams;
import com.lj.generator.params.GenTableConfigSaveOrUpdateParams;
import com.lj.generator.result.*;
import com.lj.generator.service.GenColumnConfigService;
import com.lj.generator.service.GenTableConfigService;
import com.lj.generator.utils.GenUtils;
import com.lj.generator.utils.TypeMapper;
import com.lj.mp.standard.StandardEntity;
import com.lj.mp.standard.StandardServiceImpl;
import com.lj.mp.utils.PageQueryUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.reflect.Field;
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
                .tablePrefix(underline.length > 2 ? underline[0] : "")
                .columnInfoList(columnInfoList)
                .build();
    }

    private final List<SuperClassInfo> superClassList = new ArrayList<>();


    @Override
    public List<String> optionalSuperClass() {
        return superClassList.stream().map(SuperClassInfo::getName).collect(Collectors.toList());
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
        // 父类字段名与字段类型映射
        Map<String, String> superClassFieldNameTypeMap = superClassList.stream().filter(sc -> sc.getName().equals(superClass))
                .map(SuperClassInfo::getFieldInfoList)
                .findFirst()
                .orElseThrow(() -> new CommonException("不支持的父类:" + superClass))
                .stream().collect(Collectors.toMap(SuperClassFieldInfo::getName, SuperClassFieldInfo::getFieldType));
        // 物理表信息
        TableInfoResult tableInfoResult = loadTableInfo(params.getTableName());
        // 校验该父类是否可以分配给该物理表StandardEntity没有一个字段，所有实体都可以继承
        if (CollUtil.isNotEmpty(superClassFieldNameTypeMap)) {
            Set<String> temp = new HashSet<>();
            List<ColumnInfoResult> columnInfoList = tableInfoResult.getColumnInfoList();
            for (ColumnInfoResult columnInfoResult : columnInfoList) {
                String fieldType = superClassFieldNameTypeMap.get(columnInfoResult.getFieldName());
                if (fieldType == null) {
                    continue;
                }
                if (!columnInfoResult.getFieldType().equals(fieldType)) {
                    throw new CommonException("父类字段: " + columnInfoResult.getFieldName() + " 与数据库列: " + columnInfoResult.getColumnName() + "类型不匹配!");
                }
                temp.add(columnInfoResult.getFieldName());
            }
            List<String> dissatisfyFieldName = CollUtil.subtractToList(superClassFieldNameTypeMap.keySet(), temp);
            if (CollUtil.isNotEmpty(dissatisfyFieldName)) {
                throw new CommonException("无法将父类分配给表,缺少字段:" + dissatisfyFieldName);
            }
        }
        // 校验物理表的字段是否与要生成的表匹配
        Map<String, GenColumnConfigSaveOrUpdateParams> columnNameColumnConfig = params.getColumnConfigList().stream().collect(Collectors.toMap(GenColumnConfigSaveOrUpdateParams::getColumnName, Function.identity()));
        List<ColumnInfoResult> columnInfoList = tableInfoResult.getColumnInfoList();
        for (ColumnInfoResult columnInfoResult : columnInfoList) {
            GenColumnConfigSaveOrUpdateParams columnParams = columnNameColumnConfig.get(columnInfoResult.getColumnName());
            if (columnParams == null) {
                throw new CommonException("缺少列: " + columnInfoResult.getColumnName() + " 配置");
            }
            String fieldType = columnInfoResult.getFieldType();
            // 字典枚举类型去字段类型优先级高
            String enumDictType = columnParams.getEnumDictType();
            if (StrUtil.isNotBlank(enumDictType)) {
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
            } else if (!fieldType.equals(columnParams.getFieldType())) {
                throw new CommonException("字段与列类型不匹配" + columnParams.getFieldType() + "->" + fieldType);
            }
        }
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
