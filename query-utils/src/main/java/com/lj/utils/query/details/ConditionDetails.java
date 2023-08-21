package com.lj.utils.query.details;

import com.lj.utils.query.condition.ConditionHandler;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.annotation.Annotation;

/**
 * @author luojing
 * @date 2023/8/18
 */
@Data
@AllArgsConstructor
public abstract class ConditionDetails<A extends Annotation> {

    /**
     * 条件注解
     */
    private AnnotationDetails<A> annotationDetails;

    /**
     * 条件处理器
     */
    private ConditionHandler<A> handler;
}
