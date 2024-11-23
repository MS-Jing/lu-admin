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
 * @since 2024/11/20 18:34
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ServiceImplInfo extends AbstractTemplateInfo {

    public ServiceImplInfo(GenTemplateInfo genTemplateInfo) {
        super(genTemplateInfo);

        String packagePath = StrUtil.join(StrPool.DOT,
                genTemplateInfo.getPackageName(),
                genTemplateInfo.getModuleName(),
                GenConstant.servicePackageName,
                GenConstant.serviceImplPackageName);
        setPackagePath(packagePath);
        String className = genTemplateInfo.getEntity().getClassName() + GenConstant.serviceImplSuffix;
        setClassName(className);
        setFileName(className + GenConstant.javaFileSuffix);
        setFilePath(StrUtil.join(FileUtil.FILE_SEPARATOR,
                genTemplateInfo.getModuleName(),
                GenConstant.javaDir,
                packagePath.replace(StrPool.DOT, FileUtil.FILE_SEPARATOR)));
    }
}
