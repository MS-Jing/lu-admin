package com.lj.generator.result.gen;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.lj.generator.constant.GenConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author luojing
 * @since 2024/11/22 10:42
 * 保存参数
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class SaveParamInfo extends AbstractTemplateInfo {

    public SaveParamInfo(GenTemplateInfo genTemplateInfo) {
        super(genTemplateInfo);

        Set<String> packages = new HashSet<>();
        List<FieldInfo> fieldInfoList = genTemplateInfo.getFieldInfoList();
        for (FieldInfo fieldInfo : fieldInfoList) {
            if (!fieldInfo.getShowInSave()) {
                // 不出现在保存的字段不用检查
                continue;
            }
            if (fieldInfo.getRequired()) {
                // 必填字段需要校验
                if (ClassUtil.getClassName(String.class, true).equals(fieldInfo.getFieldType())) {
                    packages.add(ClassUtil.getClassName(NotBlank.class, false));
                } else {
                    packages.add(ClassUtil.getClassName(NotNull.class, false));
                }
            }
            if (fieldInfo.getEnumDict() != null) {
                // 字段需要转换成entity实体,所以需要把字典导入
                packages.add(fieldInfo.getEnumDict().getClassName());
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
        String className = genTemplateInfo.getEntity().getClassName() + GenConstant.saveParamSuffix;
        setClassName(className);
        setFileName(className + GenConstant.javaFileSuffix);
        setFilePath(StrUtil.join(FileUtil.FILE_SEPARATOR,
                genTemplateInfo.getModuleName(),
                GenConstant.javaDir,
                packagePath.replace(StrPool.DOT, FileUtil.FILE_SEPARATOR)));
    }
}
