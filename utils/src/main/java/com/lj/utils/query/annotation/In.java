package com.lj.utils.query.annotation;

import java.lang.annotation.*;

/**
 * @author luojing
 * @date 2023/8/18
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface In {
    /**
     * 字段为空时不加入查询条件
     */
    boolean notNull() default true;

    /**
     * 数据库字段真实列名，可以是驼峰类型，不填为标注的字段名
     */
    String column() default "";

    /**
     * 是否需要取反 false 为不取反，true就是 not in
     */
    boolean not() default false;
}
