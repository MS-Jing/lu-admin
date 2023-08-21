package com.lj.utils.query.annotation;

import java.lang.annotation.*;

/**
 * @author luojing
 * @date 2023/8/10
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OrderBy {
    /**
     * 可以升序排序的字段
     */
    String[] asc() default {};

    /**
     * 可以降序排序的字段
     */
    String[] desc() default {};
}
