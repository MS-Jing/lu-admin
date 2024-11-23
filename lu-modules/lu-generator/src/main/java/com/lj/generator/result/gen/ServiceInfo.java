package com.lj.generator.result.gen;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.generator.constant.GenConstant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author luojing
 * @since 2024/11/20 18:21
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ServiceInfo extends AbstractTemplateInfo {

    public ServiceInfo(GenTemplateInfo genTemplateInfo) {
        super(genTemplateInfo);
        Set<String> packages = new HashSet<>();
        if (genTemplateInfo.getGenPage()) {
            packages.add(ClassUtil.getClassName(IPage.class, false));
            packages.add(genTemplateInfo.getPageParam().getClassPath());
            packages.add(genTemplateInfo.getPageResult().getClassPath());
        }
        if (genTemplateInfo.getGenInfo()) {
            packages.add(genTemplateInfo.getInfoResult().getClassPath());
        }
        if (genTemplateInfo.getGenSave()) {
            packages.add(genTemplateInfo.getSaveParam().getClassPath());
        }
        if (genTemplateInfo.getGenUpdate()) {
            packages.add(genTemplateInfo.getUpdateParam().getClassPath());
        }
        setPackages(packages);
        String packagePath = StrUtil.join(StrPool.DOT,
                genTemplateInfo.getPackageName(),
                genTemplateInfo.getModuleName(),
                GenConstant.servicePackageName);
        setPackagePath(packagePath);
        String className = genTemplateInfo.getEntity().getClassName() + GenConstant.serviceSuffix;
        setClassName(className);
        setFileName(className + GenConstant.javaFileSuffix);
        List<String> filePath = CollUtil.newArrayList(GenConstant.backEndDir);
        filePath.addAll(GenConstant.javaDir);
        CollUtil.addAll(filePath, StrUtil.split(packagePath, StrPool.DOT));
        setFilePath(filePath);
    }

    @Override
    public String getTemplate() {
        return GenConstant.serviceTemplate;
    }
}
