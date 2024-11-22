package com.lj.generator.constant;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @author luojing
 * @since 2024/11/21 17:04
 */
public interface GenConstant {
    /**
     * 实体所在包名
     */
    String entityPackageName = "entity";

    String javaFileSuffix = ".java";

    String javaDir = StrUtil.join(FileUtil.FILE_SEPARATOR, "src", "main", "java");

    String entityTemplate = "/templates/entity.java.ftl";


}
