package com.lj.utils.query.condition;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import com.lj.utils.query.AbstractQueryParams;
import com.lj.utils.query.QueryWrapper;
import com.lj.utils.query.WrapperFun;
import com.lj.utils.query.annotation.In;
import com.lj.utils.query.details.AnnotationDetails;
import com.lj.utils.query.details.ParamsFieldDetail;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * @author luojing
 * @date 2023/8/18
 */
@Slf4j
public class InConditionHandler extends FieldGeneralConditionHandler<In> {
    @Override
    public AnnotationDetails<In> getAnnotationDetails(Class<? extends AbstractQueryParams> paramsClass, Field paramField, In conditionAnnotation) {
        // 先校验字段类型
        Class<?> paramClass = TypeUtil.getClass(paramField);
        if (!ClassUtil.isAssignable(Collection.class, paramClass)) {
            log.error("{}字段被@In注解修饰，字段类型必须是Collection类型及其子类！", paramField.getName());
            throw new RuntimeException("被@In注解修饰的字段，字段类型必须是Collection类型！");
        }
        // 设置注解信息
        AnnotationDetails<In> annotationDetails = new AnnotationDetails<>(conditionAnnotation);
        annotationDetails.setNotNull(conditionAnnotation.notNull());
        annotationDetails.setNot(conditionAnnotation.not());
        String column = conditionAnnotation.column();
        annotationDetails.setColumn(StrUtil.isNotBlank(column) ? column : paramField.getName());
        annotationDetails.setOr(conditionAnnotation.or());
        return annotationDetails;
    }

    @Override
    protected <T> WrapperFun getFun(QueryWrapper<T> queryWrapper, ParamsFieldDetail<In> fieldDetails, Object fieldValue) {
        AnnotationDetails<In> annotationDetails = fieldDetails.getAnnotationDetails();
        if (annotationDetails.isNot()) {
            return queryWrapper::notIn;
        } else {
            return queryWrapper::in;
        }
    }
}
