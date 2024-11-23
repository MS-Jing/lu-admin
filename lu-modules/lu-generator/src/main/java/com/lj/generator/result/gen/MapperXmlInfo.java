package com.lj.generator.result.gen;

import cn.hutool.core.collection.CollUtil;
import com.lj.generator.constant.GenConstant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author luojing
 * @date 2024/11/23
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class MapperXmlInfo extends AbstractTemplateInfo {
    public MapperXmlInfo(GenTemplateInfo genTemplateInfo) {
        super(genTemplateInfo);
        String className = genTemplateInfo.getEntity().getClassName() + GenConstant.mapperSuffix;
        setFileName(className + GenConstant.xmlFileSuffix);
        List<String> filePath = CollUtil.newArrayList(GenConstant.backEndDir);
        filePath.addAll(GenConstant.mapperDir);
        setFilePath(filePath);
    }

    @Override
    public String getTemplate() {
        return GenConstant.mapperXmlTemplate;
    }
}
