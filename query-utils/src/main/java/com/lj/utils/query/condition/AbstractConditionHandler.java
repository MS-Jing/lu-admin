package com.lj.utils.query.condition;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.lj.utils.query.AbstractQueryParams;
import com.lj.utils.query.details.ConditionDetails;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 * @author luojing
 * @date 2023/8/18
 * 所有的条件处理器都需要继承该类
 */
public abstract class AbstractConditionHandler<A extends Annotation> implements ConditionHandler<A> {

    @Override
    public ConditionDetails<A> getConditionDetails(Class<? extends AbstractQueryParams> paramsClass, Field paramField, Annotation conditionAnnotation) {
        return handlerConditionDetails(paramsClass, paramField, (A) conditionAnnotation);
    }

    public abstract ConditionDetails<A> handlerConditionDetails(Class<? extends AbstractQueryParams> paramsClass, Field paramField, A conditionAnnotation);


    protected boolean isNotNull(Object fieldValue) {
        if (fieldValue instanceof String) {
            return StrUtil.isNotBlank((String) fieldValue);
        }
        if (fieldValue instanceof Collection) {
            return CollUtil.isNotEmpty((Collection) fieldValue);
        }

        if (fieldValue instanceof Map) {
            return CollUtil.isNotEmpty((Map) fieldValue);
        }

        return ObjectUtil.isNotNull(fieldValue);
    }

    protected String transformColumn(String column) {
        return StrUtil.toUnderlineCase(column);
    }
}
