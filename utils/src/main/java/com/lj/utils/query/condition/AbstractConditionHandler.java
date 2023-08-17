package com.lj.utils.query.condition;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.lj.utils.query.AbstractQueryParams;
import com.lj.utils.query.ParamsFieldDetail;
import com.lj.utils.query.QueryWrapper;
import com.lj.utils.query.WrapperFun;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 * @author luojing
 * @date 2023/8/11
 * 抽象的条件注解处理器
 */
public abstract class AbstractConditionHandler<A extends Annotation> {

    public ParamsFieldDetail<A> handleParamsField(Field paramField, A condition) {
        return new ParamsFieldDetail<>(paramField, this, condition);
    }

    public <T> void handleCondition(QueryWrapper<T> queryWrapper, ParamsFieldDetail<A> fieldDetails, AbstractQueryParams queryParams) {
        Object fieldValue = ReflectUtil.getFieldValue(queryParams, fieldDetails.getParamField());
        WrapperFun wrapperFun = getFun(queryWrapper, fieldDetails, fieldValue);
        if (wrapperFun != null) {
            // 这是在判断queryWrapper的查询条件是否需要对字段值进行判空
            boolean isNotNull = !valueNotNull(fieldDetails) || this.isNotNull(fieldValue);
            wrapperFun.condition(isNotNull, getColumn(fieldDetails), fieldValue);
        } else {
            handleCondition(queryWrapper, fieldDetails, fieldValue);
        }
    }

    /**
     * 判断是否需要判断值为空
     */
    protected boolean valueNotNull(ParamsFieldDetail<A> fieldDetails) {
        return true;
    }

    /**
     * 获取数据库真实字段名称
     */
    protected String getColumn(ParamsFieldDetail<A> fieldDetails) {
        return getColumn(fieldDetails.getParamField().getName(), "");
    }

    protected <T> void handleCondition(QueryWrapper<T> queryWrapper, ParamsFieldDetail<A> fieldDetails, Object fieldValue) {
        throw new RuntimeException("方法不被支持 请确保实现handleCondition和getFun其一");
    }

    protected <T> WrapperFun getFun(QueryWrapper<T> queryWrapper, ParamsFieldDetail<A> fieldDetails, Object fieldValue) {
        return null;
    }

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

    /**
     * 数据库列名
     * 因为列名可能会出现在注解上，如果没有那么使用字段名称，但是需要驼峰转下划线
     *
     * @param column       注解上的列名
     * @param defaultValue 字段的名称
     * @return 数据库列名
     */
    protected String getColumn(String column, String defaultValue) {
        return StrUtil.toUnderlineCase(StrUtil.isNotBlank(column) ? column : defaultValue);
    }

}
