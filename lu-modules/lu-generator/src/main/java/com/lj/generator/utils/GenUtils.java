package com.lj.generator.utils;

import cn.hutool.core.util.StrUtil;
import com.lj.common.utils.CheckUtils;

/**
 * @author luojing
 * @since 2024/11/14 17:42
 * 生成所需要的工具类
 */
public class GenUtils {

    /**
     * 列名转换为java字段名
     *
     * @param columnName 列名
     * @return java字段名
     */
    public static String columnNameToFieldName(String columnName) {
        CheckUtils.ifBlank(columnName, "列名为空?");
        return StrUtil.toCamelCase(columnName);
    }

    /**
     * java字段名转换为列名
     *
     * @param fieldName java字段名
     * @return 列名
     */
    public static String fieldNameToColumnName(String fieldName) {
        CheckUtils.ifBlank(fieldName, "字段名为空?");
        return StrUtil.toUnderlineCase(fieldName);
    }
}
