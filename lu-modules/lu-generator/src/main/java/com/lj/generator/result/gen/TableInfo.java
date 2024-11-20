package com.lj.generator.result.gen;

import lombok.Data;

/**
 * @author luojing
 * @since 2024/11/20 15:13
 */
@Data
public class TableInfo {
    /**
     * 表备注
     */
    private String comment;

    private String name;

    /**
     * controller的名字
     */
    private String controllerName;
}
