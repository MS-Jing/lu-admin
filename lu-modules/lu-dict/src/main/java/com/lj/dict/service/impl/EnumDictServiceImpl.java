package com.lj.dict.service.impl;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import com.lj.common.enums.ICommonEnum;
import com.lj.common.utils.ClassUtils;
import com.lj.dict.params.DictQueryParams;
import com.lj.dict.result.DictItem;
import com.lj.dict.result.EnumDict;
import com.lj.dict.service.EnumDictService;
import com.lj.mp.standard.IStandardEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author luojing
 * @date 2024/11/15
 */
@Service
@Slf4j
public class EnumDictServiceImpl implements EnumDictService, InitializingBean {

    /**
     * 字典名称与字典信息映射
     */
    private final Map<String, EnumDict> enumDictMap = new HashMap<>();


    @Override
    public List<EnumDict> getDict(DictQueryParams params) {
        return enumDictMap.values().stream()
                // 是空的条件就全部返回
                .filter(dict -> StrUtil.isBlank(params.getName()) || dict.getName().equals(params.getName()))
                .filter(dict -> params.getStandard() == null || dict.getStandard().equals(params.getStandard()))
                .filter(dict -> StrUtil.isBlank(params.getValueType()) || dict.getValueType().equals(params.getValueType()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DictItem<Object>> getDictItemByName(String dictName) {
        EnumDict enumDict = enumDictMap.get(dictName);
        if (enumDict == null) {
            return Collections.emptyList();
        }
        return enumDict.getDictItemList();
    }


    private void init() {
        // 扫描类路径下所有 实现ICommonEnum的枚举
        Set<Class<?>> enumClassSet = ClassUtil.scanPackageBySuper("", ICommonEnum.class);
        for (Class<?> enumClass : enumClassSet) {
            // 不是枚举的排除 例如IStandardEnum接口
            if (!ClassUtil.isEnum(enumClass)) {
                continue;
            }
            EnumDict enumDict = toEnumDictDto(enumClass);
            if (enumDict != null) {
                enumDictMap.put(enumDict.getName(), enumDict);
            }
        }
    }

    private EnumDict toEnumDictDto(Class<?> enumClass) {
        // 获取名称和描述
        String name;
        String description = "";
        com.lj.common.enums.EnumDict annotation = AnnotationUtil.getAnnotation(enumClass, com.lj.common.enums.EnumDict.class);
        if (annotation != null) {
            name = annotation.name();
            description = annotation.description();
        } else {
            name = ClassUtil.getClassName(enumClass, true);
        }
        EnumDict temp = enumDictMap.get(name);
        if (temp != null) {
            log.warn("无法添加枚举({})作为字典,因为有与之同名的枚举({})",
                    ClassUtil.getClassName(enumClass, false), name);
            return null;
        }
        EnumDict enumDict = new EnumDict();
        enumDict.setName(name);
        enumDict.setClassName(ClassUtil.getClassName(enumClass, false));
        enumDict.setSimpleClassName(ClassUtil.getClassName(enumClass, true));
        enumDict.setDescription(description);
        // 值类型
        Class<?> valueType = getTypeArgument(enumClass);
        // 在lang包下可直接使用,所以不需要全类名
        enumDict.setValueType(ClassUtils.getClassName(valueType));
        enumDict.setStandard(IStandardEnum.class.isAssignableFrom(enumClass));
        List<DictItem<Object>> dictItemList = new ArrayList<>();
        for (Object enumConstant : enumClass.getEnumConstants()) {
            ICommonEnum<Object> commonEnum = (ICommonEnum<Object>) enumConstant;
            DictItem<Object> dictItem = new DictItem<>();
            dictItem.setValue(commonEnum.getValue());
            dictItem.setLabel(commonEnum.getDesc());
            dictItemList.add(dictItem);
        }
        enumDict.setDictItemList(dictItemList);
        return enumDict;
    }

    private Class<?> getTypeArgument(Class<?> aClass) {
        ParameterizedType parameterizedType = TypeUtil.toParameterizedType(aClass, 1);
        return TypeUtil.getClass(parameterizedType.getActualTypeArguments()[0]);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("加载枚举字典...");
        long start = System.currentTimeMillis();
        init();
        log.info("加载枚举字典加载完毕 {} ms", System.currentTimeMillis() - start);
    }
}
