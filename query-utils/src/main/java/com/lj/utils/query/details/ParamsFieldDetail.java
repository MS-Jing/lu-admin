package com.lj.utils.query.details;

import com.lj.utils.query.condition.ConditionHandler;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author luojing
 * @date 2023/8/10
 * 参数字段详情
 */
@Getter
public class ParamsFieldDetail<A extends Annotation> extends ConditionDetails<A> {
    /**
     * 真实字段
     */
    private Field paramField;

    public ParamsFieldDetail(AnnotationDetails<A> annotationDetails, ConditionHandler<A> handler, Field paramField) {
        this(annotationDetails, handler);
        this.paramField = paramField;
    }

    private ParamsFieldDetail(AnnotationDetails<A> annotationDetails, ConditionHandler<A> handler) {
        super(annotationDetails, handler);
    }
}
