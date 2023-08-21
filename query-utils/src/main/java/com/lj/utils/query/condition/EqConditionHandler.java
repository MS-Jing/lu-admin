package com.lj.utils.query.condition;

import cn.hutool.core.util.StrUtil;
import com.lj.utils.query.AbstractQueryParams;
import com.lj.core.utils.QueryWrapper;
import com.lj.utils.query.WrapperFun;
import com.lj.utils.query.annotation.Eq;
import com.lj.utils.query.details.AnnotationDetails;
import com.lj.utils.query.details.ParamsFieldDetail;

import java.lang.reflect.Field;

/**
 * @author luojing
 * @date 2023/8/11
 */
public class EqConditionHandler extends FieldGeneralConditionHandler<Eq> {

    @Override
    public AnnotationDetails<Eq> getAnnotationDetails(Class<? extends AbstractQueryParams> paramsClass, Field paramField, Eq conditionAnnotation) {
        AnnotationDetails<Eq> annotationDetails = new AnnotationDetails<>(conditionAnnotation);
        annotationDetails.setNotNull(conditionAnnotation.notNull());
        String column = conditionAnnotation.column();
        annotationDetails.setColumn(StrUtil.isNotBlank(column) ? column : paramField.getName());
        annotationDetails.setNot(conditionAnnotation.not());
        annotationDetails.setOr(conditionAnnotation.or());
        return annotationDetails;
    }

    @Override
    protected <T> WrapperFun getFun(QueryWrapper<T> queryWrapper, ParamsFieldDetail<Eq> fieldDetails, Object fieldValue) {
        AnnotationDetails<Eq> annotationDetails = fieldDetails.getAnnotationDetails();
        if (annotationDetails.isNot()) {
            return queryWrapper::ne;
        } else {
            return queryWrapper::eq;
        }
    }
}
