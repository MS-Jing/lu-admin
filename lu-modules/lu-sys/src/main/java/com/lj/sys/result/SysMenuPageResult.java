package com.lj.sys.result;

import com.lj.sys.entity.SysMenu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * <p>
 * 菜单表 分页结果
 * </p>
 *
 * @author lj
 * @since 2024-12-11 14:36:37
 */
@Data
@Schema(description = "菜单表 分页结果")
public class SysMenuPageResult {

    private Long id;

    @Schema(description = "父菜单id 顶级菜单为0 ")
    private Long parentId;

    @Schema(description = "菜单类型 参考字典: SysMenuType")
    private Integer menuType;

    @Schema(description = "菜单路由 ")
    private String path;

    @Schema(description = "菜单名称 ")
    private String name;

    @Schema(description = "菜单组件 ")
    private String component;

    @Schema(description = "菜单标题 ")
    private String title;

    @Schema(description = "模块名(只加载当前有的模块菜单) ")
    private String moduleName;

    private LocalDateTime createTime;

    public static SysMenuPageResult of(SysMenu entity) {
        if (entity == null) {
            return null;
        }
        SysMenuPageResult result = new SysMenuPageResult();
        result.setId(entity.getId());
        result.setParentId(entity.getParentId());
        result.setMenuType(entity.getMenuType().getValue());
        result.setPath(entity.getPath());
        result.setName(entity.getName());
        result.setComponent(entity.getComponent());
        result.setTitle(entity.getTitle());
        result.setModuleName(entity.getModuleName());
        result.setCreateTime(entity.getCreateTime());
        return result;
    }
}