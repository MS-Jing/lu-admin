package com.lj.utils.query;

import com.baomidou.mybatisplus.core.enums.SqlLike;

import static com.baomidou.mybatisplus.core.enums.SqlKeyword.NOT_LIKE;

/**
 * @author luojing
 * @date 2023/8/11
 * 自己的LambdaQueryWrapper用于解决硬编码的问题和拓展问题
 */
public class QueryWrapper<T> extends com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<T> {

    public QueryWrapper<T> notLikeLeft(boolean condition, String column, Object val) {
        likeValue(condition, NOT_LIKE, column, val, SqlLike.LEFT);
        return this;
    }

    public QueryWrapper<T> notLikeRight(boolean condition, String column, Object val) {
        likeValue(condition, NOT_LIKE, column, val, SqlLike.RIGHT);
        return this;
    }
}
