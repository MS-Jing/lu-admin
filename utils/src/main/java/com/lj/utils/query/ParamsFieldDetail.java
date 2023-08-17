package com.lj.utils.query;

import com.lj.utils.query.condition.AbstractConditionHandler;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author luojing
 * @date 2023/8/10
 * 参数字段详情
 */
@Data
@AllArgsConstructor
public class ParamsFieldDetail<A extends Annotation> {
//    /**
//     * 数据库真实列名
//     */
//    private String column;
//
//    /**
//     * 是否需要对字段值判空
//     */
//    private boolean notNull;

    /**
     * 真实字段
     */
    private Field paramField;

    /**
     * 字段的处理器
     */
    private AbstractConditionHandler<A> handler;

    /**
     * 条件注解
     */
    private A condition;

}
