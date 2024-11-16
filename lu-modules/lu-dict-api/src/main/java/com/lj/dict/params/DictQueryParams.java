package com.lj.dict.params;

import com.lj.dict.dto.DictQueryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author luojing
 * @date 2024/11/16
 */
@Data
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

    public DictQueryDto toDto() {
        DictQueryDto dictQueryDto = new DictQueryDto();
        dictQueryDto.setName(name);
        dictQueryDto.setStandard(standard);
        dictQueryDto.setValueType(valueType);
        return dictQueryDto;
    }
}
