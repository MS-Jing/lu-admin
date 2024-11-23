package com.lj.generator.result.gen;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lj.common.utils.ClassUtils;
import com.lj.generator.constant.GenConstant;
import com.lj.generator.result.SuperClassInfo;
import com.lj.generator.utils.GenUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author luojing
 * @since 2024/11/20 17:32
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class EntityInfo extends AbstractTemplateInfo {

    /**
     * 父类实体名称
     */
    private String superEntityClass;


    public EntityInfo(GenTemplateInfo genTemplateInfo, SuperClassInfo superClassInfo) {
        super(genTemplateInfo);
        Set<String> packages = new HashSet<>();
        packages.add(superClassInfo.getName());
        List<FieldInfo> fieldInfoList = genTemplateInfo.getFieldInfoList();
        for (FieldInfo fieldInfo : fieldInfoList) {
            if (fieldInfo.isExistSuperClass()) {
                // 在父类的字段无需导入包
                continue;
            }
            if (fieldInfo.isPk()) {
                // 主键id不在父类
                packages.add(ClassUtil.getClassName(TableId.class, false));
            }
            if (fieldInfo.isConvert()) {
                // 字段需要转换
                packages.add(ClassUtil.getClassName(TableField.class, false));
            }
            if (fieldInfo.getEnumDict() != null) {
                // 需要把字典导入，字典的优先级大于原类型
                packages.add(fieldInfo.getEnumDict().getClassName());
                continue;
            }
            if (StrUtil.isNotBlank(fieldInfo.getImportType())) {
                // 原类型需要导入包
                packages.add(fieldInfo.getImportType());
            }
        }
        setPackages(packages);
        this.superEntityClass = ClassUtils.getClassSimpleName(superClassInfo.getName());
        String packagePath = StrUtil.join(StrPool.DOT,
                genTemplateInfo.getPackageName(),
                genTemplateInfo.getModuleName(),
                GenConstant.entityPackageName);
        setPackagePath(packagePath);
        String className = GenUtils.tableNameToClassName(genTemplateInfo.getTableName(),
                Boolean.TRUE.equals(genTemplateInfo.getUnprefix()) ? genTemplateInfo.getTablePrefix() : "");
        setClassName(className);
        setFileName(className + GenConstant.javaFileSuffix);
        setFilePath(StrUtil.join(FileUtil.FILE_SEPARATOR,
                genTemplateInfo.getModuleName(),
                GenConstant.javaDir,
                packagePath.replace(StrPool.DOT, FileUtil.FILE_SEPARATOR)));
    }
}
