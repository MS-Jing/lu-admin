package com.lj.generator.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author luojing
 * @since 2024/11/21 16:30
 * 预览响应结果
 */
@Data
@Accessors(chain = true)
public class GenPreviewResult {

    @Schema(description = "生成的文件路径")
    private List<String> pathList;

    @Schema(description = "文件名")
    private String fileName;

    @Schema(description = "内容")
    private String content;
}
