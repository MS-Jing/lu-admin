package com.lj.generator.result.gen;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import com.lj.generator.constant.GenConstant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author luojing
 * @since 2024/11/20 18:40
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ControllerInfo extends AbstractTemplateInfo {

    /**
     * RequestMapping 后面的url
     */
    private String url;

    public ControllerInfo(GenTemplateInfo genTemplateInfo) {
        super(genTemplateInfo);
        url = StrUtil.toUnderlineCase(genTemplateInfo.getEntity().getClassName());
        String packagePath = StrUtil.join(StrPool.DOT,
                genTemplateInfo.getPackageName(),
                genTemplateInfo.getModuleName(),
                GenConstant.controllerPackageName);
        setPackagePath(packagePath);
        String className = genTemplateInfo.getEntity().getClassName() + GenConstant.controllerSuffix;
        setClassName(className);
        setFileName(className + GenConstant.javaFileSuffix);
        List<String> filePath = CollUtil.newArrayList(GenConstant.backEndDir);
        filePath.addAll(GenConstant.javaDir);
        CollUtil.addAll(filePath, StrUtil.split(packagePath, StrPool.DOT));
        setFilePath(filePath);
    }

    @Override
    public String getTemplate() {
        return GenConstant.controllerTemplate;
    }
}
