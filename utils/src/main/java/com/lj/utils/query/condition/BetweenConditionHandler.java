package com.lj.utils.query.condition;

import cn.hutool.core.util.ReflectUtil;
import com.lj.utils.query.AbstractQueryParams;
import com.lj.utils.query.QueryWrapper;
import com.lj.utils.query.annotation.Between;
import com.lj.utils.query.details.AnnotationDetails;
import com.lj.utils.query.details.BetweenDetails;
import com.lj.utils.query.details.ConditionDetails;

import java.lang.reflect.Field;

/**
 * @author luojing
 * @date 2023/8/17
 */
public class BetweenConditionHandler extends AbstractConditionHandler<Between> {

    @Override
    public ConditionDetails<Between> handlerConditionDetails(Class<? extends AbstractQueryParams> paramsClass, Field paramField, Between conditionAnnotation) {
        AnnotationDetails<Between> annotationDetails = new AnnotationDetails<>(conditionAnnotation);
        annotationDetails.setNotNull(conditionAnnotation.notNull());
        annotationDetails.setColumn(conditionAnnotation.column());
        annotationDetails.setNot(conditionAnnotation.not());
        annotationDetails.setOr(conditionAnnotation.or());
        return new BetweenDetails<>(annotationDetails, this, paramsClass);
    }

    @Override
    public <T> void handleCondition(QueryWrapper<T> queryWrapper, ConditionDetails<Between> conditionDetails, AbstractQueryParams queryParams) {
        BetweenDetails<Between> betweenDetails = (BetweenDetails<Between>) conditionDetails;
        AnnotationDetails<Between> annotationDetails = betweenDetails.getAnnotationDetails();
        Between between = annotationDetails.getConditionAnnotation();
        Object leftFieldValue = ReflectUtil.getFieldValue(queryParams, between.leftField());
        Object rightFieldValue = ReflectUtil.getFieldValue(queryParams, between.rightField());
        boolean isNotNull = true;

        if (annotationDetails.isNotNull()) {
            isNotNull = isNotNull(leftFieldValue) && isNotNull(rightFieldValue);
        }
        if (annotationDetails.isOr()) {
            queryWrapper.or(isNotNull);
        }
        String column = transformColumn(annotationDetails.getColumn());
        if (between.not()) {
            queryWrapper.notBetween(isNotNull, column, leftFieldValue, rightFieldValue);
        } else {
            queryWrapper.between(isNotNull, column, leftFieldValue, rightFieldValue);
        }
    }
}
