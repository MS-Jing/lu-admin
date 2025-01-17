package com.lj.dict.params;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @author luojing
 * @date 2024/11/16
 */
@Data
@Builder
public class DictQueryParams {
    /**
     * 字典名称
     */
    @Schema(description = "字典名称")
    private String name;

    /**
     * 是否是框架定义的标准枚举
     */
    @Schema(description = "是否只筛选标准枚举字典")
    private Boolean standard;

    /**
     * 字典值类型
     */
    @Schema(description = "枚举字典值类型")
    private String valueType;
}
