package com.lj.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lj.sys.standard.SysStandardEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author lj
 * @since 2024-08-13
 */
@Getter
@Setter
@TableName("sys_menu")
public class SysMenu extends SysStandardEntity {

    private static final long serialVersionUID = 1L;

    private Long parentId;

    private String title;

    private String name;

    private Integer menuType;

    private String path;

    private String component;

    private String icon;

    private Integer sortCode;


}
