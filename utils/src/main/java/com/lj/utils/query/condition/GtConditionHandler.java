package com.lj.utils.query.condition;

import com.lj.utils.query.ParamsFieldDetail;
import com.lj.utils.query.QueryWrapper;
import com.lj.utils.query.WrapperFun;
import com.lj.utils.query.annotation.Gt;

import java.lang.reflect.Field;

/**
 * @author luojing
 * @date 2023/8/17
 */
public class GtConditionHandler extends AbstractConditionHandler<Gt> {

    @Override
    protected boolean valueNotNull(ParamsFieldDetail<Gt> fieldDetails) {
        return fieldDetails.getCondition().notNull();
    }

    @Override
    protected String getColumn(ParamsFieldDetail<Gt> fieldDetails) {
        Gt condition = fieldDetails.getCondition();
        Field paramField = fieldDetails.getParamField();
        return this.getColumn(condition.column(), paramField.getName());
    }

    @Override
    protected <T> WrapperFun getFun(QueryWrapper<T> queryWrapper, ParamsFieldDetail<Gt> fieldDetails, Object fieldValue) {
        Gt condition = fieldDetails.getCondition();
        if (condition.equal()) {
            return queryWrapper::ge;
        }
        return queryWrapper::gt;
    }
}
