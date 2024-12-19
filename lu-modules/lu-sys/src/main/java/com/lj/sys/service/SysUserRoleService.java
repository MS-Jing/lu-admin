package com.lj.sys.service;

import com.lj.mp.standard.StandardService;
import com.lj.sys.entity.SysUserRole;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author lj
 * @since 2024-12-10 17:08:40
 */
public interface SysUserRoleService extends StandardService<SysUserRole> {

    /**
     * 根据角色id 删除与该角色关联的 用户角色关联信息
     *
     * @param roleId 角色id
     */
    default void deleteByRoleId(Long roleId) {
        this.remove(lambdaQueryWrapper()
                .eq(SysUserRole::getRoleId, roleId));
    }

}