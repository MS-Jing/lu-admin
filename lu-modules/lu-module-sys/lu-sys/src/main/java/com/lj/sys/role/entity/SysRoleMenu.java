package com.lj.sys.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lj.sys.standard.SysStandardEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author lj
 * @since 2024-08-13
 */
@Getter
@Setter
@TableName("sys_role_menu")
public class SysRoleMenu extends SysStandardEntity {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long menuId;


}
