package com.lj.utils.query.condition;

import com.lj.utils.query.ParamsFieldDetail;
import com.lj.utils.query.QueryWrapper;
import com.lj.utils.query.WrapperFun;
import com.lj.utils.query.annotation.Lt;

import java.lang.reflect.Field;

/**
 * @author luojing
 * @date 2023/8/17
 */
public class LtConditionHandler extends AbstractConditionHandler<Lt> {

    @Override
    protected boolean valueNotNull(ParamsFieldDetail<Lt> fieldDetails) {
        return fieldDetails.getCondition().notNull();
    }

    @Override
    protected String getColumn(ParamsFieldDetail<Lt> fieldDetails) {
        Lt condition = fieldDetails.getCondition();
        Field paramField = fieldDetails.getParamField();
        return this.getColumn(condition.column(), paramField.getName());
    }

    @Override
    protected <T> WrapperFun getFun(QueryWrapper<T> queryWrapper, ParamsFieldDetail<Lt> fieldDetails, Object fieldValue) {
        Lt condition = fieldDetails.getCondition();
        if (condition.equal()) {
            return queryWrapper::le;
        }
        return queryWrapper::lt;
    }
}
