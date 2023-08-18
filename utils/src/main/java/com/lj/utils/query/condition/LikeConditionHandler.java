package com.lj.utils.query.condition;

import cn.hutool.core.util.StrUtil;
import com.lj.utils.query.AbstractQueryParams;
import com.lj.utils.query.QueryWrapper;
import com.lj.utils.query.WrapperFun;
import com.lj.utils.query.annotation.Like;
import com.lj.utils.query.details.AnnotationDetails;
import com.lj.utils.query.details.ParamsFieldDetail;

import java.lang.reflect.Field;

/**
 * @author luojing
 * @date 2023/8/11
 */
public class LikeConditionHandler extends FieldGeneralConditionHandler<Like> {

    @Override
    public AnnotationDetails<Like> getAnnotationDetails(Class<? extends AbstractQueryParams> paramsClass, Field paramField, Like conditionAnnotation) {
        AnnotationDetails<Like> annotationDetails = new AnnotationDetails<>(conditionAnnotation);
        annotationDetails.setNotNull(conditionAnnotation.notNull());
        String column = conditionAnnotation.column();
        annotationDetails.setColumn(StrUtil.isNotBlank(column) ? column : paramField.getName());
        annotationDetails.setNot(conditionAnnotation.not());
        return annotationDetails;
    }

    @Override
    protected <T> WrapperFun getFun(QueryWrapper<T> queryWrapper, ParamsFieldDetail<Like> fieldDetails, Object fieldValue) {
        Like condition = fieldDetails.getAnnotationDetails().getConditionAnnotation();
        return condition.model().getFun(queryWrapper, condition.not());
    }
}
