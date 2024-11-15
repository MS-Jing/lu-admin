package com.lj.dict.service.impl;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.TypeUtil;
import com.lj.common.enums.EnumDict;
import com.lj.common.enums.ICommonEnum;
import com.lj.dict.service.EnumDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author luojing
 * @date 2024/11/15
 */
@Service
@Slf4j
public class EnumDictServiceImpl implements EnumDictService, InitializingBean {

    /**
     * 名称与枚举的映射
     */
    private Map<String, Class<?>> nameEnumMap = new HashMap<>();
    /**
     * 值类型与枚举的映射
     */
    private MultiValueMap<String, Class<?>> valueTypeEnumMap = new LinkedMultiValueMap<>();

    private void init() {
        Set<Class<?>> classes = ClassUtil.scanPackageBySuper("", ICommonEnum.class);
        String langPackage = ClassUtil.getPackage(Object.class);
        for (Class<?> aClass : classes) {
            // 不是枚举的排除 例如IStandardEnum接口
            if (!ClassUtil.isEnum(aClass)) {
                continue;
            }
            // 获取名称
            String name;
            EnumDict annotation = AnnotationUtil.getAnnotation(aClass, EnumDict.class);
            if (annotation != null) {
                name = annotation.name();
            } else {
                name = ClassUtil.getClassName(aClass, true);
            }
            Class<?> old = nameEnumMap.get(name);
            if (old != null) {
                log.warn("无法添加枚举({})作为字典,因为有与之同名的枚举({})",
                        ClassUtil.getClassName(aClass, false), ClassUtil.getClassName(old, false));
                continue;
            }
            // 添加名称映射
            nameEnumMap.put(name, aClass);
            // 值类型
            Class<?> valueType = getTypeArgument(aClass);
            if (langPackage.equals(ClassUtil.getPackage(valueType))) {
                // 在lang包下可直接使用,所以不需要全类名
                valueTypeEnumMap.add(ClassUtil.getClassName(valueType, true), aClass);
            } else {
                valueTypeEnumMap.add(ClassUtil.getClassName(valueType, false), aClass);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("加载枚举字典...");
        long start = System.currentTimeMillis();
        init();
        log.info("加载枚举字典加载完毕 {} ms", System.currentTimeMillis() - start);
    }

    private Class<?> getTypeArgument(Class<?> aClass) {
        ParameterizedType parameterizedType = TypeUtil.toParameterizedType(aClass, 1);
        return TypeUtil.getClass(parameterizedType.getActualTypeArguments()[0]);
    }
}
