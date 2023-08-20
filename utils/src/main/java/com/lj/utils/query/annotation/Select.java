package com.lj.utils.query.annotation;

import java.lang.annotation.*;

/**
 * @author luojing
 * @date 2023/8/20
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Select {
    String[] value();
}
