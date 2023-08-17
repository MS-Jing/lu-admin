package com.lj.utils.query.annotation;

import java.lang.annotation.*;

/**
 * @author luojing
 * @date 2023/8/10
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CanOrder {
    /**
     * 该字段是否需要排序
     */
    boolean canOrder() default true;

    /**
     * 排序方式是否是升序
     */
    boolean isAsc() default true;
}
