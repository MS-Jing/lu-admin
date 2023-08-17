package com.lj.utils.query.condition;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.lj.utils.query.AbstractQueryParams;
import com.lj.utils.query.BetweenDetails;
import com.lj.utils.query.QueryWrapper;
import com.lj.utils.query.annotation.Between;

import java.util.Collection;
import java.util.Map;

/**
 * @author luojing
 * @date 2023/8/17
 */
public class BetweenConditionHandler {

    public <T> void handleCondition(QueryWrapper<T> queryWrapper, BetweenDetails betweenDetails, AbstractQueryParams queryParams) {
        Between condition = betweenDetails.getCondition();
        Object leftFieldValue = ReflectUtil.getFieldValue(queryParams, condition.leftField());
        Object rightFieldValue = ReflectUtil.getFieldValue(queryParams, condition.rightField());
        boolean isNotNull = true;
        if (condition.notNull()) {
            isNotNull = isNotNull(leftFieldValue) && isNotNull(rightFieldValue);
        }
        String column = StrUtil.toUnderlineCase(condition.column());
        if (condition.not()) {
            queryWrapper.notBetween(isNotNull, column, leftFieldValue, rightFieldValue);
        } else {
            queryWrapper.between(isNotNull, column, leftFieldValue, rightFieldValue);
        }
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
}
