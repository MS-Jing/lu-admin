package com.lj.generator.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import com.lj.common.utils.CheckUtils;
import com.lj.common.utils.ClassUtils;
import com.lj.generator.entity.GenTableConfig;
import com.lj.generator.result.ColumnInfoResult;
import com.lj.generator.result.SuperClassFieldInfo;
import com.lj.generator.result.SuperClassInfo;
import com.lj.generator.result.TableInfoResult;
import com.lj.generator.mapper.GenTableConfigMapper;
import com.lj.generator.service.GenTableConfigService;
import com.lj.generator.utils.GenUtils;
import com.lj.generator.utils.TypeMapper;
import com.lj.mp.standard.StandardEntity;
import com.lj.mp.standard.StandardServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.*;
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
