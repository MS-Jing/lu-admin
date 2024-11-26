package com.lj.common.utils.excel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.lj.common.utils.CheckUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author luojing
 * @since 2024/11/25 11:18
 * Excel工具类，需要提供excel的导入导出流，如果是web,提供web的输入流或响应流就好了
 */
public class ExcelUtil {

    /**
     * 导出excel 用到的easy-excel 工具，可以进行多sheet导出
     *
     * @param out         导出流
     * @param excelSheets sheet 信息 参考：{@link ExcelSheet}
     */
    public static void exportExcel(OutputStream out, ExcelSheet<?>... excelSheets) {
        CheckUtils.ifEmpty(excelSheets, "导出excel sheet不能为空！");
        try (ExcelWriter excelWriter = EasyExcel.write(out)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .build()) {
            int i = 1;
            for (ExcelSheet<?> excelSheet : excelSheets) {
                ExcelWriterSheetBuilder sheetBuilder = EasyExcel
                        .writerSheet(StrUtil.isBlank(excelSheet.getSheetName()) ? "Sheet" + (i++) : excelSheet.getSheetName());
                if (excelSheet.getHeadType() != null) {
                    sheetBuilder.head(excelSheet.getHeadType());
                } else if (CollUtil.isNotEmpty(excelSheet.getHeadList())) {
                    sheetBuilder.head(excelSheet.getHeadList());
                }
                WriteSheet writeSheet = sheetBuilder.build();
                excelWriter.write(excelSheet.getDataList(), writeSheet);
            }
            excelWriter.finish();
        }
    }

    /**
     * 异步读取excel
     *
     * @param in       excel输入流
     * @param headType 表头类型
     * @param listener easyexcel 的监听器
     * @param <T>      数据类型
     */
    public static <T> void readExcel(InputStream in, Class<T> headType, ReadListener<T> listener) {
        readExcel(in, headType, (Integer) null, listener);
    }

    /**
     * 异步读取excel
     *
     * @param in       excel输入流
     * @param headType 表头类型
     * @param sheetNo  读第几个sheet
     * @param listener easyexcel 的监听器
     * @param <T>      数据类型
     */
    public static <T> void readExcel(InputStream in, Class<T> headType, Integer sheetNo, ReadListener<T> listener) {
        EasyExcel.read(in, headType, listener).sheet(sheetNo).doRead();
    }

    /**
     * 异步读取excel
     *
     * @param in        excel输入流
     * @param headType  表头类型
     * @param sheetName sheet的名称
     * @param listener  easyexcel 的监听器
     * @param <T>       数据类型
     */
    public static <T> void readExcel(InputStream in, Class<T> headType, String sheetName, ReadListener<T> listener) {
        EasyExcel.read(in, headType, listener).sheet(sheetName).doRead();
    }

    /**
     * 同步读取excel 如果excel的数据量大的话不推荐使用，因为会把excel的所有数据读取到内存中
     *
     * @param in       excel输入流
     * @param headType 表头类型
     * @param <T>      数据类型
     * @return excel数据
     */
    public static <T> List<T> readExcel(InputStream in, Class<T> headType) {
        return readExcel(in, headType, (Integer) null);
    }

    /**
     * 同步读取excel 如果excel的数据量大的话不推荐使用，因为会把excel的所有数据读取到内存中
     *
     * @param in       excel输入流
     * @param headType 表头类型
     * @param sheetNo  读第几个sheet
     * @param <T>      数据类型
     * @return excel数据
     */
    public static <T> List<T> readExcel(InputStream in, Class<T> headType, Integer sheetNo) {
        return EasyExcel.read(in).head(headType).sheet(sheetNo).doReadSync();
    }

    /**
     * 同步读取excel 如果excel的数据量大的话不推荐使用，因为会把excel的所有数据读取到内存中
     *
     * @param in        excel输入流
     * @param headType  表头类型
     * @param sheetName sheet的名称
     * @param <T>       数据类型
     * @return excel数据
     */
    public static <T> List<T> readExcel(InputStream in, Class<T> headType, String sheetName) {
        return EasyExcel.read(in).head(headType).sheet(sheetName).doReadSync();
    }
}
