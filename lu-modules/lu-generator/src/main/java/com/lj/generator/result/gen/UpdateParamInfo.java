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
 * 更新参数
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class UpdateParamInfo extends AbstractTemplateInfo {

    public UpdateParamInfo(GenTemplateInfo genTemplateInfo) {
        super(genTemplateInfo);
        Set<String> packages = new HashSet<>();
        List<FieldInfo> fieldInfoList = genTemplateInfo.getFieldInfoList();
        for (FieldInfo fieldInfo : fieldInfoList) {
            if (!fieldInfo.getShowInUpdate()) {
                // 不出现在保存的字段不用检查
                continue;
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
        String className = genTemplateInfo.getEntity().getClassName() + GenConstant.updateParamSuffix;
        setClassName(className);
        setFileName(className + GenConstant.javaFileSuffix);
        List<String> filePath = CollUtil.newArrayList(GenConstant.backEndDir);
        filePath.addAll(GenConstant.javaDir);
        CollUtil.addAll(filePath, StrUtil.split(packagePath, StrPool.DOT));
        setFilePath(filePath);
    }

    @Override
    public String getTemplate() {
        return GenConstant.updateParamTemplate;
    }
}
