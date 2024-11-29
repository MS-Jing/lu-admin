package com.lj.dict.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author luojing
 * @date 2024/11/16
 */
@Data
public class EnumDictResult {

    /**
     * 字典名称
     */
    @Schema(description = "字典名称")
    private String name;

    /**
     * 字典描述
     */
    @Schema(description = "字典描述")
    private String description;

    /**
     * 是否是后端定义的标准枚举
     */
    @Schema(description = "是否是后端定义的标准枚举")
    private Boolean standard;

    /**
     * 字典值类型
     */
    @Schema(description = "字典值类型")
    private String valueType;

    public static EnumDictResult of(EnumDict enumDict) {
        EnumDictResult enumDictResult = new EnumDictResult();
        enumDictResult.setName(enumDict.getName());
        enumDictResult.setDescription(enumDict.getDescription());
        enumDictResult.setStandard(enumDict.getStandard());
        enumDictResult.setValueType(enumDict.getValueType());
        return enumDictResult;
    }
}
