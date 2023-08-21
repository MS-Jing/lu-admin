package com.lj.utils.query.condition;

import com.lj.utils.query.AbstractQueryParams;
import com.lj.utils.query.QueryWrapper;
import com.lj.utils.query.details.ConditionDetails;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author luojing
 * @date 2023/8/17
 * 条件注解处理器接口
 */
public interface ConditionHandler<A extends Annotation> {
    /**
     * 获取条件详情类
     *
     * @param paramsClass         参数类对象
     * @param paramField          参数字段 如果是类上的注解就会为空
     * @param conditionAnnotation 条件注解
     * @return 条件详情类
     */
    ConditionDetails<A> getConditionDetails(Class<? extends AbstractQueryParams> paramsClass, Field paramField, Annotation conditionAnnotation);

    /**
     * 处理条件注解标注的条件
     *
     * @param queryWrapper     queryWrapper
     * @param conditionDetails 条件详情类
     * @param queryParams      真实的请求参数
     * @param <T>              对应表的实体类类型
     */
    <T> void handleCondition(QueryWrapper<T> queryWrapper, ConditionDetails<A> conditionDetails, AbstractQueryParams queryParams);
}
