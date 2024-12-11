package com.lj.sys.param;

import com.lj.sys.entity.SysMenu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.lj.sys.enums.SysMenuType;

/**
 * <p>
 * 菜单表 更新参数
 * </p>
 *
 * @author lj
 * @since 2024-12-11 14:36:37
 */
@Data
@Schema(description = "菜单表 更新参数")
public class SysMenuUpdateParam {

    private Long id;

    @Schema(description = "父菜单id 顶级菜单为0")
    private Long parentId;

    @Schema(description = "菜单类型 参考字典: SysMenuType")
    private Integer menuType;

    @Schema(description = "菜单路由")
    private String path;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "菜单组件")
    private String component;

    @Schema(description = "重定向")
    private String redirect;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "菜单标题")
    private String title;

    @Schema(description = "当前菜单隐藏时才需要设置")
    private String activeMenu;

    @Schema(description = "链接")
    private String link;

    @Schema(description = "在菜单上隐藏")
    private Boolean hide;

    @Schema(description = "充满整个屏幕")
    private Boolean full;

    @Schema(description = "是否始终依附在tab上")
    private Boolean affix;

    @Schema(description = "保持活跃")
    private Boolean keepAlive;

    @Schema(description = "菜单排序")
    private Integer sortCode;

    @Schema(description = "模块名(只加载当前有的模块菜单)")
    private String moduleName;

    public SysMenu toEntity() {
        SysMenu entity = new SysMenu();
        entity.setId(id);
        entity.setParentId(parentId);
        entity.setMenuType(SysMenuType.ofByValue(menuType));
        entity.setPath(path);
        entity.setName(name);
        entity.setComponent(component);
        entity.setRedirect(redirect);
        entity.setIcon(icon);
        entity.setTitle(title);
        entity.setActiveMenu(activeMenu);
        entity.setLink(link);
        entity.setHide(hide);
        entity.setFull(full);
        entity.setAffix(affix);
        entity.setKeepAlive(keepAlive);
        entity.setSortCode(sortCode);
        entity.setModuleName(moduleName);
        return entity;
    }
}