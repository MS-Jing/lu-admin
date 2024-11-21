package com.lj.common.utils;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ClassUtil;

/**
 * @author luojing
 * @date 2024/11/16
 * 类相关工具类
 */
public class ClassUtils extends ClassUtil {
    /**
     * lang包包名，lang包下的类型可以直接拿来使用
     */
    private static final String langPackage = ClassUtil.getPackage(Object.class);

    /**
     * 获取类型名称 如果lang包下的直接返回类名
     * 例如 Class<String> -> String;
     * Class<BigDecimal> -> java.math.BigDecimal
     * <p/>
     * 如果 你不想让lang包下的类失去全类名 {@link ClassUtil#getClassName(Class, boolean)} 第二个参数为false
     *
     * @param clazz 类
     * @return 类型字符串
     */
    public static String getClassName(Class<?> clazz) {
        return ClassUtil.getClassName(clazz, langPackage.equals(ClassUtil.getPackage(clazz)));
    }

    /**
     * 通过className的名称返回类的简易名字
     * java.lang.String -> String
     * String -> String
     * java.math.BigDecimal -> BigDecimal
     * BigDecimal -> BigDecimal
     *
     * @param className className
     * @return className
     */
    public static String getClassSimpleName(String className) {
        int i = className.lastIndexOf(StrPool.DOT);
        if (i == -1) {
            return className;
        }
        return className.substring(i+1);
    }
}
