package com.lj.utils.query.details;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.lang.annotation.Annotation;

/**
 * @author luojing
 * @date 2023/8/18
 * 注解的详情类
 */
@Data
public class AnnotationDetails<A extends Annotation> {

    /**
     * 条件注解
     */
    @Setter(AccessLevel.NONE)
    private A conditionAnnotation;

    /**
     * 判断字段为空时是否不加入查询条件
     */
    private boolean notNull = true;

    /**
     * 列名
     */
    private String column;

    /**
     * 是否要取反 就是加上not
     */
    private boolean not;

    /**
     * 是否 用or来拼接，默认是and
     */
    private boolean or;

    public AnnotationDetails(A conditionAnnotation) {
        this.conditionAnnotation = conditionAnnotation;
    }
}
