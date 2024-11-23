package com.lj.generator.result.gen;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import com.lj.generator.constant.GenConstant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author luojing
 * @since 2024/11/22 10:42
 * 分页结果
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class PageResultInfo extends AbstractTemplateInfo {

    public PageResultInfo(GenTemplateInfo genTemplateInfo) {
        super(genTemplateInfo);
        Set<String> packages = new HashSet<>();
        List<FieldInfo> fieldInfoList = genTemplateInfo.getFieldInfoList();
        for (FieldInfo fieldInfo : fieldInfoList) {
            if (!fieldInfo.getShowInList()) {
                // 不出现在查询结果的字段不用检查
                continue;
            }
            // 不要直接用字典返回值
            if (StrUtil.isNotBlank(fieldInfo.getImportType())) {
                // 原类型需要导入包
                packages.add(fieldInfo.getImportType());
            }
        }
        setPackages(packages);
        String packagePath = StrUtil.join(StrPool.DOT,
                genTemplateInfo.getPackageName(),
                genTemplateInfo.getModuleName(),
                GenConstant.resultPackageName);
        setPackagePath(packagePath);
        String className = genTemplateInfo.getEntity().getClassName() + GenConstant.pageResultSuffix;
        setClassName(className);
        setFileName(className + GenConstant.javaFileSuffix);
        List<String> filePath = CollUtil.newArrayList(GenConstant.backEndDir);
        filePath.addAll(GenConstant.javaDir);
        CollUtil.addAll(filePath, StrUtil.split(packagePath, StrPool.DOT));
        setFilePath(filePath);
    }

    @Override
    public String getTemplate() {
        return GenConstant.pageResultTemplate;
    }
}
