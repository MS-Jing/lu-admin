package com.lj.utils.query.details;

import com.lj.utils.query.AbstractQueryParams;
import com.lj.utils.query.condition.ConditionHandler;
import lombok.Getter;

import java.lang.annotation.Annotation;

/**
 * @author luojing
 * @date 2023/8/17
 * 专门的区间查询详情类
 */
@Getter
public class BetweenDetails<A extends Annotation> extends ConditionDetails<A> {
    /**
     * 区间查询的参数类
     */
    private Class<? extends AbstractQueryParams> paramsClass;


    public BetweenDetails(AnnotationDetails<A> annotationDetails, ConditionHandler<A> handler, Class<? extends AbstractQueryParams> paramsClass) {
        this(annotationDetails, handler);
        this.paramsClass = paramsClass;
    }

    private BetweenDetails(AnnotationDetails<A> annotationDetails, ConditionHandler<A> handler) {
        super(annotationDetails, handler);
    }
}
