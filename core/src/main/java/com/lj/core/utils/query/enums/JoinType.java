package com.lj.core.utils.query.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author luojing
 * @date 2023/8/22
 * 连表查询的类型
 */
@AllArgsConstructor
@Getter
public enum JoinType {

    /**
     * 连表查询的类型
     */
    LEFT("left join"),
    RIGHT("right join"),
    INNER("inner join");

    private String value;
}
