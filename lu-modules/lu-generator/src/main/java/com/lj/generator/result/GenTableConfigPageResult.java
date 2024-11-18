package com.lj.generator.result;

import com.lj.generator.entity.GenTableConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author luojing
 * @since 2024/11/18 17:37
 */
@Data
@Schema(description = "生成表分页信息")
public class GenTableConfigPageResult {

    private Long id;

    @Schema(description = "表名")
    private String tableName;

    @Schema(description = "表注释")
    private String comment;

    @Schema(description = "最后配置时间")
    private LocalDateTime updateTime;

    public static GenTableConfigPageResult of(GenTableConfig tableConfig) {
        if (tableConfig == null) {
            return null;
        }
        GenTableConfigPageResult result = new GenTableConfigPageResult();
        result.setId(tableConfig.getId());
        result.setTableName(tableConfig.getTableName());
        result.setComment(tableConfig.getComment());
        result.setUpdateTime(tableConfig.getUpdateTime());
        return result;
    }
}
