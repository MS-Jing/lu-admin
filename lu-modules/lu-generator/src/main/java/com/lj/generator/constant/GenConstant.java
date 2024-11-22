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

    /**
     * service所在包名
     */
    String servicePackageName = "service";
    String serviceImplPackageName = "impl";

    String mapperPackageName = "mapper";

    /**
     * 参数所在包名
     */
    String paramPackageName = "param";

    String resultPackageName = "result";

    String serviceSuffix = "Service";
    String serviceImplSuffix = "ServiceImpl";
    String mapperSuffix = "Mapper";

    /**
     * 分页参数文件后缀名
     */
    String pageParamSuffix = "PageParam";

    String pageResultSuffix = "PageResult";

    String infoResultSuffix = "InfoResult";
    String saveParamSuffix = "SaveParam";
    String updateParamSuffix = "UpdateParam";

    String javaFileSuffix = ".java";

    String javaDir = StrUtil.join(FileUtil.FILE_SEPARATOR, "src", "main", "java");

    String entityTemplate = "/templates/entity.java.ftl";
    String pageParamTemplate = "/templates/pageParam.java.ftl";
    String pageResultTemplate = "/templates/pageResult.java.ftl";
    String infoResultTemplate = "/templates/infoResult.java.ftl";
    String saveParamTemplate = "/templates/saveParam.java.ftl";
    String updateParamTemplate = "/templates/updateParam.java.ftl";
    String serviceTemplate = "/templates/service.java.ftl";
    String serviceImplTemplate = "/templates/serviceImpl.java.ftl";
    String mapperTemplate = "/templates/mapper.java.ftl";


}
