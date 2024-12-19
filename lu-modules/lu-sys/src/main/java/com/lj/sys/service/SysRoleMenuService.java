package com.lj.sys.service;

import com.lj.mp.standard.StandardService;
import com.lj.sys.entity.SysRoleMenu;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author lj
 * @since 2024-12-10 17:27:54
 */
public interface SysRoleMenuService extends StandardService<SysRoleMenu> {

    /**
     * 刷新角色与菜单的关系
     *
     * @param roleId     角色id
     * @param menuIdList 菜单id列表 如果是空得会删除该角色所有菜单权限
     */
    void refresh(Long roleId, List<Long> menuIdList);
}