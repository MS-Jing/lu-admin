package com.lj.utils.query.condition;

import com.lj.utils.query.ParamsFieldDetail;
import com.lj.utils.query.annotation.Between;

import java.lang.reflect.Field;

/**
 * @author luojing
 * @date 2023/8/17
 */
public class BetweenConditionHandler extends AbstractConditionHandler<Between> {
//    @Override
//    public ParamsFieldDetail handleParamsField(Field paramField, Between condition) {
//        return new ParamsFieldDetail(this.getColumn(condition.column(), paramField.getName()), condition.notNull(), paramField, this, condition);
//    }
}
