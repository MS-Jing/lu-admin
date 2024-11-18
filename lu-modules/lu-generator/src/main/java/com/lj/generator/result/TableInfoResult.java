package com.lj.generator.result;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author luojing
 * @since 2024/11/12 18:06
 */
@Data
@Builder
public class TableInfoResult {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表前缀
     */
    private String tablePrefix;

    /**
     * 表备注
     */
    private String comment;

    /**
     * 列信息列表
     */
    private List<ColumnInfoResult> columnInfoList;
}
