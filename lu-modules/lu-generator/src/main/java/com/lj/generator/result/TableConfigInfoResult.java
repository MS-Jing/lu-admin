package com.lj.generator.result;

import com.lj.generator.entity.GenTableConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author luojing
 * @since 2024/12/5 17:40
 */
@Data
public class TableConfigInfoResult {
    @Schema(description = "更新的时候必填")
    private Long id;

    @Schema(description = "表名")
    private String tableName;

    @Schema(description = "表注释")
    private String comment;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "模块名 例如: generator")
    private String moduleName;

    @Schema(description = "包名 例如:com.lj")
    private String packageName;

    @Schema(description = "超类")
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

    @Schema(description = "是否生成导入接口")
    private Boolean genImport;

    @Schema(description = "是否生成导出接口")
    private Boolean genExport;

    @Schema(description = "列配置信息")
    private List<ColumnConfigInfoResult> columnConfigList;

    public static TableConfigInfoResult of(GenTableConfig entity) {
        if (entity == null) {
            return null;
        }
        TableConfigInfoResult result = new TableConfigInfoResult();
        result.setId(entity.getId());
        result.setTableName(entity.getTableName());
        result.setComment(entity.getComment());
        result.setAuthor(entity.getAuthor());
        result.setModuleName(entity.getModuleName());
        result.setPackageName(entity.getPackageName());
        result.setSuperClass(entity.getSuperClass());
        result.setTablePrefix(entity.getTablePrefix());
        result.setUnprefix(entity.getUnprefix());
        result.setGenPage(entity.getGenPage());
        result.setGenInfo(entity.getGenInfo());
        result.setGenSave(entity.getGenSave());
        result.setGenUpdate(entity.getGenUpdate());
        result.setGenDeleted(entity.getGenDeleted());
        result.setGenImport(entity.getGenImport());
        result.setGenExport(entity.getGenExport());
        return result;
    }
}
