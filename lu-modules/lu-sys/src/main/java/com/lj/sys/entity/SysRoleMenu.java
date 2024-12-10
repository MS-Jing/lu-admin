package com.lj.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.lj.mp.standard.entity.IdLongStandardEntity;

/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author lj
 * @since 2024-12-10 17:27:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_menu")
public class SysRoleMenu extends IdLongStandardEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 菜单id
     */
    private Long menuId;
}