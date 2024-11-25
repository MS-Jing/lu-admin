package com.lj.common.utils;

import lombok.Getter;

import java.util.List;

/**
 * @author luojing
 * @since 2024/11/25 14:28
 * excelSheet表信息
 */
@Getter
public class ExcelSheet<T> {

    /**
     * sheet名
     */
    private final String sheetName;

    /**
     * 数据列表
     */
    private final List<T> dataList;

    /**
     * 表头类型 优先级高于headList
     */
    private Class<T> headType;

    /**
     * 表头
     */
    private List<List<String>> headList;

    public ExcelSheet(List<T> dataList, Class<T> headType) {
        this(null, dataList, headType);
    }

    public ExcelSheet(String sheetName, List<T> dataList, Class<T> headType) {
        this.sheetName = sheetName;
        this.dataList = dataList;
        this.headType = headType;
    }

    public ExcelSheet(List<T> dataList, List<List<String>> headList) {
        this(null, dataList, headList);
    }

    public ExcelSheet(String sheetName, List<T> dataList, List<List<String>> headList) {
        this.sheetName = sheetName;
        this.dataList = dataList;
        this.headList = headList;
    }
}
