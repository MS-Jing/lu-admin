package com.lj.utils.query.condition;

import com.lj.utils.query.AbstractQueryParams;
import com.lj.core.utils.QueryWrapper;
import com.lj.utils.query.annotation.Select;
import com.lj.utils.query.details.AnnotationDetails;
import com.lj.utils.query.details.ConditionDetails;
import com.lj.utils.query.details.ParamsClassDetails;

import java.lang.reflect.Field;

/**
 * @author luojing
 * @date 2023/8/20
 */
public class SelectConditionHandler extends AbstractConditionHandler<Select> {

    @Override
    public ConditionDetails<Select> handlerConditionDetails(Class<? extends AbstractQueryParams> paramsClass, Field paramField, Select conditionAnnotation) {
        return new ParamsClassDetails<>(new AnnotationDetails<>(conditionAnnotation), this, paramsClass);
    }

    @Override
    public <T> void handleCondition(QueryWrapper<T> queryWrapper, ConditionDetails<Select> conditionDetails, AbstractQueryParams queryParams) {
        Select select = conditionDetails.getAnnotationDetails().getConditionAnnotation();
        String[] columns = select.value();
        for (int i = 0; i < columns.length; i++) {
            columns[i] = transformColumn(columns[i]);
        }
        queryWrapper.select(columns);
    }
}
