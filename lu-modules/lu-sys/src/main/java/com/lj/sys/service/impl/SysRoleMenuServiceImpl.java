package com.lj.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.lj.mp.standard.StandardServiceImpl;
import com.lj.sys.entity.SysRoleMenu;
import com.lj.sys.mapper.SysRoleMenuMapper;
import com.lj.sys.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author lj
 * @since 2024-12-10 17:27:54
 */
@Service
public class SysRoleMenuServiceImpl extends StandardServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refresh(Long roleId, Set<Long> menuIdList) {
        // 该角色拥有得菜单与主键id映射
        Map<Long, Long> menuIdOfIdMap = this.getByRoleId(roleId)
                .stream().collect(Collectors.toMap(SysRoleMenu::getMenuId, SysRoleMenu::getId));
        // 需要删除得菜单id
        List<Long> deleteMenuIdList = CollUtil.subtractToList(menuIdOfIdMap.keySet(), menuIdList);
        // 转换成主键id 批量删除
        if (CollUtil.isNotEmpty(deleteMenuIdList)) {
            this.removeBatchByIds(deleteMenuIdList.stream().map(menuIdOfIdMap::get).toList());
        }
        // 需要添加的菜单id
        List<Long> saveMenuIdList = CollUtil.subtractToList(menuIdList, menuIdOfIdMap.keySet());
        // 批量添加
        this.saveBatch(saveMenuIdList.stream().map(menuId -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenu.setMenuId(menuId);
            return sysRoleMenu;
        }).toList());
    }
}