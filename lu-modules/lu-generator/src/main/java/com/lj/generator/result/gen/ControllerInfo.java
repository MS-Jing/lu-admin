package com.lj.generator.result.gen;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import com.lj.generator.constant.GenConstant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
        setFilePath(StrUtil.join(FileUtil.FILE_SEPARATOR,
                genTemplateInfo.getModuleName(),
                GenConstant.javaDir,
                packagePath.replace(StrPool.DOT, FileUtil.FILE_SEPARATOR)));
    }
}
