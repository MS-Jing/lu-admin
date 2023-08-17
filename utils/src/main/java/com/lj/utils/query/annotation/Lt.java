package com.lj.utils.query.annotation;

import java.lang.annotation.*;

/**
 * @author luojing
 * @date 2023/8/17
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Lt {
    /**
     * 字段为空时不加入查询条件
     */
    boolean notNull() default true;

    /**
     * 数据库字段真实列名，可以是驼峰类型，不填为标注的字段名
     */
    String column() default "";

    /**
     * 是否是小于等于：<=
     */
    boolean equal() default false;
}
