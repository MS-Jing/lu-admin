package com.lj.utils.query.condition;

import com.lj.utils.query.ParamsFieldDetail;
import com.lj.utils.query.QueryWrapper;
import com.lj.utils.query.WrapperFun;
import com.lj.utils.query.annotation.Eq;

import java.lang.reflect.Field;

/**
 * @author luojing
 * @date 2023/8/11
 */
public class EqConditionHandler extends AbstractConditionHandler<Eq> {

    @Override
    protected boolean valueNotNull(ParamsFieldDetail<Eq> fieldDetails) {
        return fieldDetails.getCondition().notNull();
    }

    @Override
    protected String getColumn(ParamsFieldDetail<Eq> fieldDetails) {
        Eq condition = fieldDetails.getCondition();
        Field paramField = fieldDetails.getParamField();
        return this.getColumn(condition.column(), paramField.getName());
    }

    @Override
    protected <T> WrapperFun getFun(QueryWrapper<T> queryWrapper, ParamsFieldDetail<Eq> fieldDetails, Object fieldValue) {
        Eq condition = fieldDetails.getCondition();
        if (condition.not()) {
            return queryWrapper::ne;
        } else {
            return queryWrapper::eq;
        }
    }
}
