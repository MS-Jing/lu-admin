package com.lj.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.lj.common.exception.CommonException;

import java.util.Collection;
import java.util.Map;

/**
 * @author luojing
 * @since 2024/11/14 16:33
 * 检验工具类
 */
public class CheckUtils {

    /**
     * 判断对象如果为空抛出异常
     *
     * @param obj              判断对象
     * @param exceptionMessage 异常信息
     */
    public static void ifNull(Object obj, String exceptionMessage) {
        ifCondition(ObjectUtil.isNull(obj), exceptionMessage);
    }

    /**
     * 判断对象如果不为空抛出异常
     *
     * @param obj              判断对象
     * @param exceptionMessage 异常信息
     */
    public static void ifNotNull(Object obj, String exceptionMessage) {
        ifCondition(ObjectUtil.isNotNull(obj), exceptionMessage);
    }

    /**
     * 判断字符串如果为空抛出异常
     *
     * @param str              判断字符串
     * @param exceptionMessage 异常信息
     */
    public static void ifBlank(String str, String exceptionMessage) {
        ifCondition(StrUtil.isBlank(str), exceptionMessage);
    }

    /**
     * 判断字符串如果不为空抛出异常
     *
     * @param str              判断字符串
     * @param exceptionMessage 异常信息
     */
    public static void ifNotBlank(String str, String exceptionMessage) {
        ifCondition(StrUtil.isNotBlank(str), exceptionMessage);
    }

    /**
     * 判断集合如果为空抛出异常
     *
     * @param collection       判断集合
     * @param exceptionMessage 异常信息
     */
    public static void ifEmpty(Collection<?> collection, String exceptionMessage) {
        ifCondition(CollUtil.isEmpty(collection), exceptionMessage);
    }

    /**
     * 判断集合如果不为空抛出异常
     *
     * @param collection       判断集合
     * @param exceptionMessage 异常信息
     */
    public static void ifNotEmpty(Collection<?> collection, String exceptionMessage) {
        ifCondition(CollUtil.isNotEmpty(collection), exceptionMessage);
    }

    /**
     * 判断map如果为空抛出异常
     *
     * @param map              判断map
     * @param exceptionMessage 异常信息
     */
    public static void ifEmpty(Map<?, ?> map, String exceptionMessage) {
        ifCondition(CollUtil.isEmpty(map), exceptionMessage);
    }

    /**
     * 判断map如果不为空抛出异常
     *
     * @param map              判断map
     * @param exceptionMessage 异常信息
     */
    public static void ifNotEmpty(Map<?, ?> map, String exceptionMessage) {
        ifCondition(CollUtil.isNotEmpty(map), exceptionMessage);
    }

    public static <T> void ifEmpty(T[] array, String exceptionMessage) {
        ifCondition(ArrayUtil.isEmpty(array), exceptionMessage);
    }

    public static <T> void ifNotEmpty(T[] array, String exceptionMessage) {
        ifCondition(ArrayUtil.isNotEmpty(array), exceptionMessage);
    }


    /**
     * 判断条件如果满足抛出异常
     *
     * @param condition        判断条件
     * @param exceptionMessage 异常信息
     */
    public static void ifCondition(boolean condition, String exceptionMessage) {
        if (condition) {
            throw new CommonException(exceptionMessage);
        }
    }

}
