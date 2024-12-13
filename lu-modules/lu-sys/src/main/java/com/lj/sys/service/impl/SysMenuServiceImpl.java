package com.lj.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.common.module.ModuleHelper;
import com.lj.common.utils.CheckUtils;
import com.lj.mp.standard.StandardServiceImpl;
import com.lj.mp.utils.PageQueryUtils;
import com.lj.sys.constant.SysConstant;
import com.lj.sys.entity.SysMenu;
import com.lj.sys.enums.SysMenuType;
import com.lj.sys.mapper.SysMenuMapper;
import com.lj.sys.param.SysMenuPageParam;
import com.lj.sys.param.SysMenuSaveParam;
import com.lj.sys.param.SysMenuUpdateParam;
import com.lj.sys.result.SysMenuInfoResult;
import com.lj.sys.result.SysMenuPageResult;
import com.lj.sys.service.SysMenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author lj
 * @since 2024-12-11 14:36:37
 */
@Service
public class SysMenuServiceImpl extends StandardServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Resource
    private ModuleHelper moduleHelper;

    @Override
    public List<SysMenuInfoResult> listByUserId(Long userId) {
        // 获取当前所有的模板
        Set<String> allModuleName = moduleHelper.getAllModuleName();
        CheckUtils.ifEmpty(allModuleName, "当前没有模块可以提供服务!");
        // 超级管理员有上帝视角，可以看所有的菜单
        List<SysMenu> menuList;
        if (SysConstant.SUPER_ADMIN_ID.equals(userId)) {
            menuList = list(lambdaQueryWrapper()
                    .in(SysMenu::getMenuType, SysMenuType.MENU, SysMenuType.DIR)
                    .in(SysMenu::getModuleName, allModuleName)
                    .orderByAsc(SysMenu::getSortCode));
        } else {
            menuList = baseMapper.listByUserId(userId, allModuleName);
        }
        // 组装成树
        return assembleMenuTree(menuList);
    }

    private List<SysMenuInfoResult> assembleMenuTree(List<SysMenu> menuList) {
        if (CollUtil.isEmpty(menuList)) {
            return Collections.emptyList();
        }
        // 转换成要输出的结果
        List<SysMenuInfoResult> resultList = menuList.stream().map(SysMenuInfoResult::of).collect(Collectors.toList());
        // 组装成树
        Map<Long, SysMenuInfoResult> resultMap = resultList.stream().collect(Collectors.toMap(SysMenuInfoResult::getId, Function.identity()));
        Iterator<SysMenuInfoResult> iterator = resultList.iterator();
        while (iterator.hasNext()) {
            SysMenuInfoResult next = iterator.next();
            // 这是在找父节点，将当前节点放到父节点的children中，并在resultList中删除
            SysMenuInfoResult parent = resultMap.get(next.getParentId());
            if (ObjectUtil.isNotNull(parent)) {
                parent.getChildren().add(next);
                iterator.remove();
            }
        }
        // 最后剩下的就是跟节点0
        return resultList;
    }

    @Override
    public Map<String, List<String>> buttonPermission(Long userId) {
        // 获取当前所有的模板
        Set<String> allModuleName = moduleHelper.getAllModuleName();
        CheckUtils.ifEmpty(allModuleName, "当前没有模块可以提供服务!");
        // 超级管理员有上帝视角，可以看所有的菜单
        List<SysMenu> menuList;
        if (SysConstant.SUPER_ADMIN_ID.equals(userId)) {
            menuList = list(lambdaQueryWrapper()
                    .in(SysMenu::getMenuType, SysMenuType.BUTTON)
                    .in(SysMenu::getModuleName, allModuleName)
                    .orderByAsc(SysMenu::getSortCode));
        } else {
            menuList = baseMapper.buttonByUserId(userId, allModuleName);
        }
        if (CollUtil.isEmpty(menuList)) {
            return Collections.emptyMap();
        }
        return menuList.stream()
                .filter(m -> StrUtil.isNotBlank(m.getPermission()) && StrUtil.isNotBlank(m.getName()))
                // 分组 字典的name为页面的路由name value 为当前页面路由的所有按钮权限
                .collect(Collectors.groupingBy(SysMenu::getName, Collectors.mapping(SysMenu::getPermission, Collectors.toList())));
    }

    @Override
    public List<SysMenuInfoResult> tree() {
        // 不分模块，直接展示，这里主要用于菜单的管理
        List<SysMenu> menuList = list(lambdaQueryWrapper()
                .orderByAsc(SysMenu::getSortCode));
        return assembleMenuTree(menuList);
    }

    @Override
    public IPage<SysMenuPageResult> page(SysMenuPageParam param) {
        return this.page(PageQueryUtils.getPage(param), getQueryWrapper(param)).convert(SysMenuPageResult::of);
    }

    private LambdaQueryWrapper<SysMenu> getQueryWrapper(SysMenuPageParam param) {
        return lambdaQueryWrapper()
                .eq(ObjectUtil.isNotEmpty(param.getMenuType()), SysMenu::getMenuType, param.getMenuType())
                .eq(ObjectUtil.isNotEmpty(param.getPath()), SysMenu::getPath, param.getPath())
                .eq(ObjectUtil.isNotEmpty(param.getName()), SysMenu::getName, param.getName())
                .eq(ObjectUtil.isNotEmpty(param.getTitle()), SysMenu::getTitle, param.getTitle())
                ;
    }

    @Override
    public SysMenuInfoResult info(Long id) {
        return SysMenuInfoResult.of(this.getById(id));
    }

    @Override
    public void save(SysMenuSaveParam param) {
        SysMenu entity = param.toEntity();
        this.save(entity);
    }

    @Override
    public void update(SysMenuUpdateParam param) {
        SysMenu entity = param.toEntity();
        this.updateById(entity);
    }
}