package com.lj.utils.query.condition;

import cn.hutool.core.util.ReflectUtil;
import com.lj.utils.query.AbstractQueryParams;
import com.lj.core.utils.query.QueryWrapper;
import com.lj.utils.query.WrapperFun;
import com.lj.utils.query.details.AnnotationDetails;
import com.lj.utils.query.details.ConditionDetails;
import com.lj.utils.query.details.ParamsFieldDetail;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author luojing
 * @date 2023/8/11
 * 抽象的条件注解处理器
 */
public abstract class FieldGeneralConditionHandler<A extends Annotation> extends AbstractConditionHandler<A> {

    @Override
    public ConditionDetails<A> handlerConditionDetails(Class<? extends AbstractQueryParams> paramsClass, Field paramField, A conditionAnnotation) {
        AnnotationDetails<A> annotationDetails = getAnnotationDetails(paramsClass, paramField, conditionAnnotation);
        return new ParamsFieldDetail<>(annotationDetails, this, paramField);
    }

    public abstract AnnotationDetails<A> getAnnotationDetails(Class<? extends AbstractQueryParams> paramsClass, Field paramField, A conditionAnnotation);

    @Override
    public <T> void handleCondition(QueryWrapper<T> queryWrapper, ConditionDetails<A> conditionDetails, AbstractQueryParams queryParams) {
        ParamsFieldDetail<A> paramsFieldDetail = (ParamsFieldDetail<A>) conditionDetails;
        AnnotationDetails<A> annotationDetails = paramsFieldDetail.getAnnotationDetails();
        Object fieldValue = ReflectUtil.getFieldValue(queryParams, paramsFieldDetail.getParamField());
        WrapperFun wrapperFun = getFun(queryWrapper, paramsFieldDetail, fieldValue);
        if (wrapperFun != null) {
            // 这是在判断queryWrapper的查询条件是否需要对字段值进行判空
            boolean isNotNull = !annotationDetails.isNotNull() || this.isNotNull(fieldValue);

            if (annotationDetails.isOr()) {
                // 如果判空后不加入最后的条件那么也不用or拼接
                queryWrapper.or(isNotNull);
            }

            String column = transformColumn(annotationDetails.getColumn());
            wrapperFun.condition(isNotNull, column, fieldValue);
        } else {
            handleCondition(queryWrapper, paramsFieldDetail, fieldValue);
        }
    }

    protected <T> void handleCondition(QueryWrapper<T> queryWrapper, ParamsFieldDetail<A> fieldDetails, Object fieldValue) {
        throw new RuntimeException("方法不被支持 请确保实现handleCondition和getFun其一");
    }

    protected <T> WrapperFun getFun(QueryWrapper<T> queryWrapper, ParamsFieldDetail<A> fieldDetails, Object fieldValue) {
        return null;
    }

}
