package com.lj.generator.params;

import com.lj.generator.entity.GenTableConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * @author luojing
 * @since 2024/11/18 18:11
 * 新增或更新参数
 */
@Data
public class GenTableConfigSaveOrUpdateParams {

    @Schema(description = "更新的时候必填")
    private Long id;

    @Schema(description = "表名")
    @NotBlank(message = "表名不能为空")
    private String tableName;

    @Schema(description = "表注释")
    private String comment;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "模块名 例如: generator")
    @NotBlank(message = "模块名不能为空")
    private String moduleName;

    @Schema(description = "包名 例如:com.lj")
    @NotBlank(message = "包名不能为空")
    private String packageName;

    @Schema(description = "超类")
    @NotBlank(message = "请选择一个超类")
    private String superClass;

    @Schema(description = "表前缀")
    private String tablePrefix;

    @Schema(description = "是否去掉表前缀 tablePrefix不为空才生效")
    private Boolean unprefix;

    @Schema(description = "是否生成分页接口")
    private Boolean genPage;

    @Schema(description = "是否生成详情接口")
    private Boolean genInfo;

    @Schema(description = "是否生成保存接口")
    private Boolean genSave;

    @Schema(description = "是否生成更新接口")
    private Boolean genUpdate;

    @Schema(description = "是否生成删除接口")
    private Boolean genDeleted;

    @Schema(description = "列配置信息")
    @NotEmpty(message = "列不能为空")
    private List<GenColumnConfigSaveOrUpdateParams> columnConfigList;

    public GenTableConfig toEntity() {
        GenTableConfig genTableConfig = new GenTableConfig();
        genTableConfig.setId(id);
        genTableConfig.setTableName(tableName);
        genTableConfig.setComment(comment);
        genTableConfig.setAuthor(author);
        genTableConfig.setModuleName(moduleName);
        genTableConfig.setPackageName(packageName);
        genTableConfig.setSuperClass(superClass);
        genTableConfig.setTablePrefix(tablePrefix);
        genTableConfig.setUnprefix(unprefix);
        genTableConfig.setGenPage(genPage);
        genTableConfig.setGenInfo(genInfo);
        genTableConfig.setGenSave(genSave);
        genTableConfig.setGenUpdate(genUpdate);
        genTableConfig.setGenDeleted(genDeleted);
        return genTableConfig;
    }
}
