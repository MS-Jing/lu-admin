package com.lj.core.utils.query;

import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.lj.core.utils.query.enums.JoinType;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.baomidou.mybatisplus.core.enums.SqlKeyword.NOT_LIKE;

/**
 * @author luojing
 * @date 2023/8/11
 * 自己的LambdaQueryWrapper用于解决硬编码的问题和拓展问题
 */
public class QueryWrapper<T> extends com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<T> {

    private List<Join> sqlJoinList;
    /**
     * 在连表查询时当前表(主表)的别名
     */
    private String alias = "self";

    public QueryWrapper<T> notLikeLeft(boolean condition, String column, Object val) {
        likeValue(condition, NOT_LIKE, column, val, SqlLike.LEFT);
        return this;
    }

    public QueryWrapper<T> notLikeRight(boolean condition, String column, Object val) {
        likeValue(condition, NOT_LIKE, column, val, SqlLike.RIGHT);
        return this;
    }

    /**
     * 在连表查询中的主表的别名设置
     *
     * @param alias 主表的别名
     */
    public QueryWrapper<T> alias(String alias) {
        this.alias = alias;
        return this;
    }

    /**
     * 左查询语句
     *
     * @param tableName 左查询需要连接的表名
     * @param alias     为该表名取的别名 注意select方法中需要加上该`别名.` 注意主表的别名: {@link QueryWrapper#alias}
     * @param on        on条件
     */
    public QueryWrapper<T> leftJoin(String tableName, String alias, String on) {
        this.addJoin(new Join(JoinType.LEFT, tableName, alias, on));
        return this;
    }

    public QueryWrapper<T> rightJoin(String tableName, String alias, String on){
        this.addJoin(new Join(JoinType.RIGHT, tableName, alias, on));
        return this;
    }

    public QueryWrapper<T> innerJoin(String tableName, String alias, String on){
        this.addJoin(new Join(JoinType.INNER, tableName, alias, on));
        return this;
    }

    private void addJoin(Join join) {
        if (this.sqlJoinList == null) {
            this.sqlJoinList = new ArrayList<>();
        }
        this.sqlJoinList.add(join);
    }

    public String getSqlJoin() {
        if (this.sqlJoinList == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" as ").append(alias).append("\n");
        for (Join join : sqlJoinList) {
            sb.append(join.joinType.getValue()).append(" ")
                    .append(join.tableName).append(" as ")
                    .append(join.alias).append(" on ")
                    .append(join.on).append("\n");
        }
        return sb.toString();
    }

    @AllArgsConstructor
    private static class Join {
        private JoinType joinType;
        private String tableName;
        private String alias;
        private String on;
    }
}
