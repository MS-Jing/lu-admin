package com.lj.utils.query.condition;

import cn.hutool.core.util.StrUtil;
import com.lj.utils.query.AbstractQueryParams;
import com.lj.core.utils.QueryWrapper;
import com.lj.utils.query.WrapperFun;
import com.lj.utils.query.annotation.Lt;
import com.lj.utils.query.details.AnnotationDetails;
import com.lj.utils.query.details.ParamsFieldDetail;

import java.lang.reflect.Field;

/**
 * @author luojing
 * @date 2023/8/17
 */
public class LtConditionHandler extends FieldGeneralConditionHandler<Lt> {

    @Override
    public AnnotationDetails<Lt> getAnnotationDetails(Class<? extends AbstractQueryParams> paramsClass, Field paramField, Lt conditionAnnotation) {
        AnnotationDetails<Lt> annotationDetails = new AnnotationDetails<>(conditionAnnotation);
        annotationDetails.setNotNull(conditionAnnotation.notNull());
        String column = conditionAnnotation.column();
        annotationDetails.setColumn(StrUtil.isNotBlank(column) ? column : paramField.getName());
        annotationDetails.setOr(conditionAnnotation.or());
        return annotationDetails;
    }

    @Override
    protected <T> WrapperFun getFun(QueryWrapper<T> queryWrapper, ParamsFieldDetail<Lt> fieldDetails, Object fieldValue) {
        Lt condition = fieldDetails.getAnnotationDetails().getConditionAnnotation();
        if (condition.equal()) {
            return queryWrapper::le;
        }
        return queryWrapper::lt;
    }
}
