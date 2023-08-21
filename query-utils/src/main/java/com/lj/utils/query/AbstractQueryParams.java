package com.lj.utils.query;

import lombok.Data;

import java.util.List;

/**
 * @author luojing
 * @date 2023/3/11
 * 抽象的查询参数
 */
@Data
public abstract class AbstractQueryParams {

    /**
     * 当前页数
     */
    private Long current = 1L;

    /**
     * 每页显示记录数
     */
    private Long size = 10L;

    /**
     * 升序排序字段 多个逗号分割
     */
    private List<String> ascOrders;

    /**
     * 降序排序字段 多个逗号分割
     */
    private List<String> descOrders;
}
