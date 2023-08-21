package com.lj.utils.query.details;

import com.lj.utils.query.AbstractQueryParams;
import com.lj.utils.query.condition.ConditionHandler;
import lombok.Getter;

import java.lang.annotation.Annotation;

/**
 * @author luojing
 * @date 2023/8/17
 * 参数类详情
 */
@Getter
public class ParamsClassDetails<A extends Annotation> extends ConditionDetails<A> {
    /**
     * 参数类
     */
    private Class<? extends AbstractQueryParams> paramsClass;


    public ParamsClassDetails(AnnotationDetails<A> annotationDetails, ConditionHandler<A> handler, Class<? extends AbstractQueryParams> paramsClass) {
        this(annotationDetails, handler);
        this.paramsClass = paramsClass;
    }

    private ParamsClassDetails(AnnotationDetails<A> annotationDetails, ConditionHandler<A> handler) {
        super(annotationDetails, handler);
    }
}
