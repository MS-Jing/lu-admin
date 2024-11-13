package com.lj.generator.entity.vo;

import lombok.Data;

/**
 * @author luojing
 * @since 2024/11/12 18:06
 */
@Data
public class TableInfoResult {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表备注
     */
    private String comment;
}
