package com.lj.generator.result.gen;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import com.lj.generator.engine.TemplateEngine;
import com.lj.generator.result.GenPreviewResult;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author luojing
 * @since 2024/11/20 17:18
 * 模板文件的一些公共的字段
 */
@Data
public abstract class AbstractTemplateInfo {
    /**
     * 包路径 例如com.lj.sys.controller
     */
    private String packagePath;

    /**
     * 要导入的包名
     * 例如： java.util.List
     */
    private Set<String> packages;

    /**
     * 生成的文件名
     */
    private String fileName;

    /**
     * 生成的文件目录
     */
    private List<String> filePath;

    /**
     * 生成的类名 如果是Java文件文件名与类名相同
     */
    private String className;

    /**
     * @return 获取类全路径类名
     */
    public String getClassPath() {
        return StrUtil.join(StrPool.DOT, packagePath, className);
    }

    public AbstractTemplateInfo(GenTemplateInfo genTemplateInfo) {
        this.packages = Collections.emptySet();
    }

    public abstract String getTemplate();

    public GenPreviewResult preview(GenTemplateInfo genTemplateInfo, TemplateEngine templateEngine) {
        return new GenPreviewResult()
                .setFileName(fileName)
                .setPathList(filePath)
                .setContent(templateEngine.preview(genTemplateInfo, getTemplate()));
    }

    public void generate(GenTemplateInfo genTemplateInfo, TemplateEngine templateEngine, String tempDir) {
        File file = new File(tempDir + FileUtil.FILE_SEPARATOR + CollUtil.join(filePath, FileUtil.FILE_SEPARATOR), fileName);
        FileUtil.mkdir(file.getParent());
        templateEngine.writer(genTemplateInfo, getTemplate(), file);
    }
}
