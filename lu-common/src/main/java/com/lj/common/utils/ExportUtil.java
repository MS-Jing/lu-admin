package com.lj.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;

import java.io.File;
import java.io.OutputStream;

/**
 * @author luojing
 * @since 2024/11/25 11:18
 * 导出工具类，需要提供一个导出流，如果web导出提供web的响应流就好了
 */
public class ExportUtil {

    private static final String xlsx = ".xlsx";

    /**
     * 导出文件
     *
     * @param file 导出的文件
     * @param out  导出流
     */
    public static void exportFile(File file, OutputStream out) {
        exportFile(FileUtil.readBytes(file), out);
    }

    /**
     * 导出文件
     *
     * @param fileBytes 文件字节码
     * @param out       导出流
     */
    public static void exportFile(byte[] fileBytes, OutputStream out) {
        IoUtil.write(out, true, fileBytes);
    }

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
}
