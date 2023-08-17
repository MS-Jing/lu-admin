package com.lj.utils.query.condition;

import com.lj.utils.query.ParamsFieldDetail;
import com.lj.utils.query.QueryWrapper;
import com.lj.utils.query.WrapperFun;
import com.lj.utils.query.annotation.Like;

import java.lang.reflect.Field;

/**
 * @author luojing
 * @date 2023/8/11
 */
public class LikeConditionHandler extends AbstractConditionHandler<Like> {

    @Override
    protected boolean valueNotNull(ParamsFieldDetail<Like> fieldDetails) {
        return fieldDetails.getCondition().notNull();
    }

    @Override
    protected String getColumn(ParamsFieldDetail<Like> fieldDetails) {
        Like condition = fieldDetails.getCondition();
        Field paramField = fieldDetails.getParamField();
        return this.getColumn(condition.column(), paramField.getName());
    }

    @Override
    protected <T> WrapperFun getFun(QueryWrapper<T> queryWrapper, ParamsFieldDetail<Like> fieldDetails, Object fieldValue) {
        Like condition = fieldDetails.getCondition();
        return condition.model().getFun(queryWrapper, condition.not());
    }
}
