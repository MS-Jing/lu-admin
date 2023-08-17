package com.lj.utils.query;

import com.lj.utils.query.annotation.Between;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author luojing
 * @date 2023/8/17
 * 专门的区间查询详情类
 */
@Data
@AllArgsConstructor
public class BetweenDetails {
    /**
     * 区间查询的参数类
     */
    private Class<? extends AbstractQueryParams> paramsClass;

    /**
     * 注解
     */
    private Between condition;
}
