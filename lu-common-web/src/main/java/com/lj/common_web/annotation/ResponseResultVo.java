package com.lj.common_web.annotation;

import java.lang.annotation.*;

/**
 * @author luojing
 * @version 1.0
 * @date 2021/7/30 21:09
 * <p>
 * 在Controller或Controller的方法上添加该注解，响应ResponseVo对象
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResultVo {

    boolean value() default true;
}