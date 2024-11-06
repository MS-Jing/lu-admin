package com.lj.mp.utils;

import lombok.Data;

import java.util.List;

/**
 * @author luojing
 * @date 2023/3/11
 * 抽象的查询参数
 */
@Data
public abstract class AbstractPageQueryParams {

    /**
     * 当前页数
     */
    private Long current = 1L;

    /**
     * 每页显示记录数
     */
    private Long size = 10L;

    /**
     * 排序列表
     * 字段:排序方式 例如: age:ASC 按照年龄 升序
     */
    private List<String> sortList;
}
