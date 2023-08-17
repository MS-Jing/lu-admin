package com.lj.utils.query.annotation;

import java.lang.annotation.*;

/**
 * @author luojing
 * @date 2023/8/17
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Between {
    /**
     * 两个字段为空时不加入查询条件
     */
    boolean notNull() default true;

    /**
     * 数据库字段真实列名，可以是驼峰类型，不填为标注的字段名
     */
    String column();

    /**
     * 左边字段的名称，会从该字段中获取值
     */
    String leftField();

    /**
     * 左边字段的名称，会从该字段中获取值
     */
    String rightField();

    /**
     * 是否需要取反 false 为等于，true 为不等于，默认是等于 就是eq
     */
    boolean not() default false;
}
