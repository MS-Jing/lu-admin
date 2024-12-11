package com.lj.sys.result;

import com.lj.sys.entity.SysMenu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 信息结果
 * </p>
 *
 * @author lj
 * @since 2024-12-11 14:36:37
 */
@Data
@Schema(description = "菜单表 信息结果")
public class SysMenuInfoResult {

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

    @Schema(description = "重定向 ")
    private String redirect;

    @Schema(description = "元标签")
    private MetaInfoResult meta;

    @Schema(description = "菜单排序 ")
    private Integer sortCode;

    @Schema(description = "模块名(只加载当前有的模块菜单) ")
    private String moduleName;

    @Schema(description = "子菜单")
    private List<SysMenuInfoResult> children = new ArrayList<>(0);

    @Data
    private static class MetaInfoResult {
        @Schema(description = "菜单图标 ")
        private String icon;

        @Schema(description = "菜单标题 ")
        private String title;

        @Schema(description = "当前菜单隐藏时才需要设置 ")
        private String activeMenu;

        @Schema(description = "链接 ")
        private String link;

        @Schema(description = "在菜单上隐藏 ")
        private Boolean hide;

        @Schema(description = "充满整个屏幕 ")
        private Boolean full;

        @Schema(description = "是否始终依附在tab上 ")
        private Boolean affix;

        @Schema(description = "保持活跃 ")
        private Boolean keepAlive;
    }

    public static SysMenuInfoResult of(SysMenu entity) {
        if (entity == null) {
            return null;
        }
        SysMenuInfoResult result = new SysMenuInfoResult();
        result.setId(entity.getId());
        result.setParentId(entity.getParentId());
        result.setMenuType(entity.getMenuType().getValue());
        result.setPath(entity.getPath());
        result.setName(entity.getName());
        result.setComponent(entity.getComponent());
        result.setRedirect(entity.getRedirect());

        MetaInfoResult metaInfoResult = new MetaInfoResult();
        result.setMeta(metaInfoResult);
        metaInfoResult.setIcon(entity.getIcon());
        metaInfoResult.setTitle(entity.getTitle());
        metaInfoResult.setActiveMenu(entity.getActiveMenu());
        metaInfoResult.setLink(entity.getLink());
        metaInfoResult.setHide(entity.getHide());
        metaInfoResult.setFull(entity.getFull());
        metaInfoResult.setAffix(entity.getAffix());
        metaInfoResult.setKeepAlive(entity.getKeepAlive());

        result.setSortCode(entity.getSortCode());
        result.setModuleName(entity.getModuleName());
        return result;
    }
}