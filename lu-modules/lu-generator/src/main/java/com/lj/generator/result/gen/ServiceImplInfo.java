package com.lj.generator.result.gen;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lj.generator.constant.GenConstant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<String> packages = new HashSet<>();
        if (genTemplateInfo.getGenPage() || genTemplateInfo.getGenExport()) {
            packages.add(ClassUtil.getClassName(LambdaQueryWrapper.class, false));
        }
        if (genTemplateInfo.getGenImport()) {
            packages.add(genTemplateInfo.getImportParam().getClassPath());
        }
        if (genTemplateInfo.getGenExport()) {
            packages.add(genTemplateInfo.getExportResult().getClassPath());
        }
        setPackages(packages);
        String packagePath = StrUtil.join(StrPool.DOT,
                genTemplateInfo.getPackageName(),
                genTemplateInfo.getModuleName(),
                GenConstant.servicePackageName,
                GenConstant.serviceImplPackageName);
        setPackagePath(packagePath);
        String className = genTemplateInfo.getEntity().getClassName() + GenConstant.serviceImplSuffix;
        setClassName(className);
        setFileName(className + GenConstant.javaFileSuffix);
        List<String> filePath = CollUtil.newArrayList(GenConstant.backEndDir);
        filePath.addAll(GenConstant.javaDir);
        CollUtil.addAll(filePath, StrUtil.split(packagePath, StrPool.DOT));
        setFilePath(filePath);
    }

    @Override
    public String getTemplate() {
        return GenConstant.serviceImplTemplate;
    }
}
