package com.lj.utils.query.condition;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.TypeUtil;
import com.lj.utils.query.AbstractQueryParams;
import com.lj.core.utils.query.QueryWrapper;
import com.lj.utils.query.annotation.AllEq;
import com.lj.utils.query.details.AnnotationDetails;
import com.lj.utils.query.details.ParamsFieldDetail;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luojing
 * @date 2023/8/14
 */
@Slf4j
public class AllEqConditionHandler extends FieldGeneralConditionHandler<AllEq> {

    @Override
    public AnnotationDetails<AllEq> getAnnotationDetails(Class<? extends AbstractQueryParams> paramsClass, Field paramField, AllEq conditionAnnotation) {
        log.warn("不建议使用AllEq注解，因为这设计到了map传参，请在请求参数类中声明要接收的参数使用Eq注解来解决");
        Type paramType = TypeUtil.getType(paramField);
        Type argument0 = TypeUtil.getTypeArgument(paramType, 0);
        Type argument1 = TypeUtil.getTypeArgument(paramType, 1);
        if (Map.class == TypeUtil.getClass(paramType) &&
                String.class == TypeUtil.getClass(argument0) &&
                Object.class == TypeUtil.getClass(argument1)) {
            return new AnnotationDetails<>(conditionAnnotation);
        }
        log.error("{}字段被@AllEq注解修饰，字段类型必须是Map<String, Object>类型！", paramField.getName());
        throw new RuntimeException("被@AllEq注解修饰的字段，字段类型必须是Map<String, Object>类型！");
    }

    @Override
    protected <T> void handleCondition(QueryWrapper<T> queryWrapper, ParamsFieldDetail<AllEq> fieldDetails, Object fieldValue) {
        AllEq annotation = fieldDetails.getAnnotationDetails().getConditionAnnotation();
        boolean isNotNull = !annotation.notNull() || this.isNotNull(fieldValue);
        Map<String, Object> params = (Map<String, Object>) fieldValue;
        Map<String, Object> map = new HashMap<>(16);
        if (CollUtil.isNotEmpty(params)) {
            params.forEach((k, v) -> map.put(transformColumn(k), v));
        }
        if (annotation.or()) {
            queryWrapper.or(isNotNull);
        }
        queryWrapper.allEq(isNotNull, map, annotation.null2IsNull());
    }
}
