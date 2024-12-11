package com.lj.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lj.sys.enums.SysMenuType;
import com.lj.sys.standard.SysStandardEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author lj
 * @since 2024-12-11 14:36:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
public class SysMenu extends SysStandardEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父菜单id 顶级菜单为0
     */
    private Long parentId;

    /**
     * 菜单类型
     */
    private SysMenuType menuType;

    /**
     * 菜单路由
     */
    private String path;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单组件
     */
    private String component;

    /**
     * 重定向
     */
    private String redirect;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 当前菜单隐藏时才需要设置
     */
    private String activeMenu;

    /**
     * 链接
     */
    private String link;

    /**
     * 在菜单上隐藏
     */
    private Boolean hide;

    /**
     * 充满整个屏幕
     */
    private Boolean full;

    /**
     * 是否始终依附在tab上
     */
    private Boolean affix;

    /**
     * 保持活跃
     */
    private Boolean keepAlive;

    /**
     * 菜单排序
     */
    private Integer sortCode;

    /**
     * 模块名(只加载当前有的模块菜单)
     */
    private String moduleName;

    /**
     * 权限(例如: sys:menu:list)
     */
    private String permission;
}