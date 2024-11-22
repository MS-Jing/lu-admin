package com.lj.generator.result.gen;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.util.Set;

/**
 * @author luojing
 * @since 2024/11/20 17:18
 * 模板文件的一下公共的字段
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
    private String filePath;

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
}
