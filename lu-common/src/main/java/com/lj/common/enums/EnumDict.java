package com.lj.common.enums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author luojing
 * @date 2024/11/15
 * 字典模块中枚举字典注解
 * 实现了{@link ICommonEnum} 的枚举使用
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumDict {

    /**
     * @return 枚举字典的名字
     */
    String name();

    /**
     * @return 字典描述
     */
    String description() default "";
}
