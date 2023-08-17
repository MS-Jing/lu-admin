package com.lj.utils.query.annotation;


import com.lj.utils.query.enums.LikeType;

import java.lang.annotation.*;

/**
 * @author luojing
 * @date 2023/3/11
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Like {
    /**
     * 字段为空时不加入查询条件
     */
    boolean notNull() default true;

    /**
     * 数据库字段真实列名，可以是驼峰类型，不填为标注的字段名
     */
    String column() default "";

    /**
     * 模式 左模糊查询，右模糊查询
     */
    LikeType model() default LikeType.AROUND;

    /**
     * 是否需要取反 例如：not like
     */
    boolean not() default false;
}
