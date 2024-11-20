package com.lj.generator.engine;

import java.io.File;
import java.util.Map;

/**
 * @author luojing
 * @since 2024/11/19 17:35
 * 模板引擎
 */
public interface TemplateEngine {


    /**
     * 初始化方法
     */
    void init();

    /**
     * 写出到文件
     *
     * @param dataModel    模板需要的数据模型
     * @param templatePath 模板的位置
     * @param outputFile   输出的文件
     */
    void writer(Map<String, Object> dataModel, String templatePath, File outputFile);

    /**
     * 写出到文件
     *
     * @param dataModel    模板需要的数据模型
     * @param templatePath 模板的位置
     * @param outputFile   输出的文件
     */
    void writer(Object dataModel, String templatePath, File outputFile);

    /**
     * 预览直接返回字符串
     *
     * @param dataModel    模板需要的数据模型
     * @param templatePath 模板的位置
     */
    String preview(Map<String, Object> dataModel, String templatePath);

    /**
     * 预览直接返回字符串
     *
     * @param dataModel    模板需要的数据模型
     * @param templatePath 模板的位置
     */
    String preview(Object dataModel, String templatePath);
}
