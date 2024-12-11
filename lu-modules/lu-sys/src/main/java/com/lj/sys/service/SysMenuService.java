package com.lj.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.mp.standard.StandardService;
import com.lj.sys.entity.SysMenu;
import com.lj.sys.param.SysMenuPageParam;
import com.lj.sys.param.SysMenuSaveParam;
import com.lj.sys.param.SysMenuUpdateParam;
import com.lj.sys.result.SysMenuInfoResult;
import com.lj.sys.result.SysMenuPageResult;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author lj
 * @since 2024-12-11 14:36:37
 */
public interface SysMenuService extends StandardService<SysMenu> {

    IPage<SysMenuPageResult> page(SysMenuPageParam param);

    SysMenuInfoResult info(Long id);

    void save(SysMenuSaveParam param);

    void update(SysMenuUpdateParam param);

    /**
     * 获取指定用户所拥有的所有菜单信息
     *
     * @param userId 用户id
     * @return 用户拥有的所有菜单信息
     */
    List<SysMenuInfoResult> listByUserId(Long userId);

    /**
     * 获取指定用户所拥有的所有页面按钮权限
     *
     * @param userId 用户id
     * @return 用户拥有的所有页面按钮权限 key为页面路由的name,value为这个页面所有按钮的权限
     */
    Map<String, List<String>> buttonPermission(Long userId);
}