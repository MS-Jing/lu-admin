package com.lj.generator.result.gen;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author luojing
 * @since 2024/11/20 14:59
 * 生成器模板需要得信息
 */
@Data
public class GenTemplateInfo {
    private PackageInfo packageInfo;

    private TableInfo tableInfo;

    private String moduleName;

    private String author;

    private String date;
}
