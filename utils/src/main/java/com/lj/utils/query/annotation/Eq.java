package com.lj.utils.query.annotation;

import java.lang.annotation.*;

/**
 * @author luojing
 * @date 2023/3/11
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Eq{

    /**
     * 字段为空时不加入查询条件
     */
    boolean notNull() default true;

    /**
     * 数据库字段真实列名，可以是驼峰类型，不填为标注的字段名
     */
    String column() default "";

    /**
     * 是否需要取反 false 为等于，true 为不等于，默认是等于 就是eq
     */
    boolean not() default false;

    /**
     * 是否 用or来拼接，默认是and
     */
    boolean or() default false;

}
