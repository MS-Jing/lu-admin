package com.lj.utils.query.annotation;

import java.lang.annotation.*;

/**
 * @author luojing
 * @date 2023/8/14
 * 不建议使用AllEq注解，因为这设计到了map传参，请在请求参数类中声明要接收的参数使用Eq注解来解决
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AllEq {

    /**
     * 字段为空时不加入查询条件
     */
    boolean notNull() default true;

    /**
     * map 所有非空属性等于 =
     * 是否参数为 null 自动执行 isNull 方法, false 则忽略这个字段
     */
    boolean null2IsNull() default true;

    /**
     * 是否 用or来拼接，默认是and
     */
    boolean or() default false;
}
