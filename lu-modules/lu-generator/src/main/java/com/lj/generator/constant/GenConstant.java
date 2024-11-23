package com.lj.generator.constant;

import cn.hutool.core.collection.CollUtil;

import java.util.List;

/**
 * @author luojing
 * @since 2024/11/21 17:04
 */
public interface GenConstant {

    /**
     * 后端目录
     */
    String backEndDir = "backend";

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

    String controllerPackageName = "controller";

    /**
     * 参数所在包名
     */
    String paramPackageName = "param";

    String resultPackageName = "result";

    String serviceSuffix = "Service";
    String serviceImplSuffix = "ServiceImpl";

    String controllerSuffix = "Controller";
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
    String xmlFileSuffix = ".xml";

    List<String> javaDir = CollUtil.newArrayList("src", "main", "java");
    List<String> mapperDir = CollUtil.newArrayList("src", "main", "resources", "mapper");

    String entityTemplate = "/templates/entity.java.ftl";
    String pageParamTemplate = "/templates/pageParam.java.ftl";
    String pageResultTemplate = "/templates/pageResult.java.ftl";
    String infoResultTemplate = "/templates/infoResult.java.ftl";
    String saveParamTemplate = "/templates/saveParam.java.ftl";
    String updateParamTemplate = "/templates/updateParam.java.ftl";
    String serviceTemplate = "/templates/service.java.ftl";
    String serviceImplTemplate = "/templates/serviceImpl.java.ftl";
    String mapperTemplate = "/templates/mapper.java.ftl";
    String mapperXmlTemplate = "/templates/mapper.xml.ftl";
    String controllerTemplate = "/templates/controller.java.ftl";


}
