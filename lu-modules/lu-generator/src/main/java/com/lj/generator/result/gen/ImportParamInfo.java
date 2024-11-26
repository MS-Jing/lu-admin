package com.lj.generator.result.gen;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.lj.common.utils.excel.CommentEnumConverter;
import com.lj.generator.constant.GenConstant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author luojing
 * @since 2024/11/26 18:12
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ImportParamInfo extends AbstractTemplateInfo {
    public ImportParamInfo(GenTemplateInfo genTemplateInfo) {
        super(genTemplateInfo);
        Set<String> packages = new HashSet<>();
        List<FieldInfo> fieldInfoList = genTemplateInfo.getFieldInfoList();
        for (FieldInfo fieldInfo : fieldInfoList) {
            if (!fieldInfo.getShowInImport()) {
                continue;
            }
            if (fieldInfo.getEnumDict() != null) {
                // 把字典类型导入
                packages.add(fieldInfo.getEnumDict().getClassName());
                // 将字典转换器导入
                packages.add(ClassUtil.getClassName(CommentEnumConverter.class, false));
            }
            if (StrUtil.isNotBlank(fieldInfo.getImportType())) {
                // 原类型需要导入包
                packages.add(fieldInfo.getImportType());
            }
        }
        setPackages(packages);
        String packagePath = StrUtil.join(StrPool.DOT,
                genTemplateInfo.getPackageName(),
                genTemplateInfo.getModuleName(),
                GenConstant.paramPackageName);
        setPackagePath(packagePath);
        String className = genTemplateInfo.getEntity().getClassName() + GenConstant.importParamSuffix;
        setClassName(className);
        setFileName(className + GenConstant.javaFileSuffix);
        List<String> filePath = CollUtil.newArrayList(GenConstant.backEndDir);
        filePath.addAll(GenConstant.javaDir);
        CollUtil.addAll(filePath, StrUtil.split(packagePath, StrPool.DOT));
        setFilePath(filePath);
    }

    @Override
    public String getTemplate() {
        return GenConstant.importParamTemplate;
    }
}
