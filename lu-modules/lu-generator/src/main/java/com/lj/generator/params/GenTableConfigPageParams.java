package com.lj.generator.params;

import com.lj.mp.utils.AbstractPageQueryParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author luojing
 * @since 2024/11/18 17:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "生成表配置分页查询参数")
public class GenTableConfigPageParams extends AbstractPageQueryParams {

    @Schema(description = "表名")
    private String tableName;
}
