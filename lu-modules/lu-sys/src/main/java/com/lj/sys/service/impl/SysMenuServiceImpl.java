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
import org.springframework.transaction.annotation.Transactional;

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
    public List<SysMenuInfoResult> tree(List<Integer> menuTypeList) {
        // 不分模块，直接展示，这里主要用于菜单的管理
        List<SysMenu> menuList = list(lambdaQueryWrapper()
                .in(CollUtil.isNotEmpty(menuTypeList), SysMenu::getMenuType, menuTypeList)
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
        verify(entity);
        this.save(entity);
    }

    @Override
    public void update(SysMenuUpdateParam param) {
        SysMenu entity = param.toEntity();
        verify(entity);
        this.updateById(entity);
    }

    /**
     * 入库前校验
     */
    private void verify(SysMenu entity) {
        CheckUtils.ifNull(entity.getMenuType(), "类型为必填项!");
        // 判断父元素
        SysMenu parentSysMenu;
        if (entity.getParentId() == null || SysConstant.ROOT_MENU_ID.equals(entity.getParentId())) {
            // 如果没有父元素或者父元素是根节点
            parentSysMenu = new SysMenu();
            parentSysMenu.setId(0L);
            parentSysMenu.setMenuType(SysMenuType.DIR);
        } else {
            parentSysMenu = this.getById(entity.getParentId());
            CheckUtils.ifNull(parentSysMenu, "没有找到 id:" + entity.getParentId() + " 父级!");
            // 检查模块名是否相同
            CheckUtils.ifCondition(!parentSysMenu.getModuleName().equals(entity.getModuleName()), "模块名请与父级一致！");
        }
        CheckUtils.ifCondition(parentSysMenu.getId().equals(entity.getId()), "错误的父级类型! 自己是自己的父级?");
        if (SysMenuType.DIR.equals(entity.getMenuType())) {
            // 目录的父级只能是目录
            CheckUtils.ifCondition(!SysMenuType.DIR.equals(parentSysMenu.getMenuType()), "错误的父级类型!");
        }
        if (SysMenuType.MENU.equals(entity.getMenuType())) {
            // 菜单的父级可以是目录，也可以是菜单(子菜单，隐藏起来的详情页) 但是不能是按钮
            CheckUtils.ifCondition(SysMenuType.BUTTON.equals(parentSysMenu.getMenuType()), "错误的父级类型!");
            CheckUtils.ifBlank(entity.getComponent(), "请填写菜单组件!");
        }
        if (SysMenuType.DIR.equals(entity.getMenuType()) || SysMenuType.MENU.equals(entity.getMenuType())) {
            // 目录与菜单需要填写路由
            CheckUtils.ifBlank(entity.getPath(), "请填写路由!");
        }
        if (SysMenuType.BUTTON.equals(entity.getMenuType())) {
            // 按钮只能出现在菜单上
            CheckUtils.ifCondition(!SysMenuType.MENU.equals(parentSysMenu.getMenuType()), "错误的父级类型!");
            // 如果是按钮将父菜单的name给到当前按钮，这里是做按钮权限的分组 请看{@code this.buttonPermission()} 方法
            entity.setName(parentSysMenu.getName());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        if (id == null) {
            return;
        }
        // 这里的逻辑时查出所有菜单，再批量删除，避免递归查询
        // 菜单表的数据不会很大，至少不会百万级
        List<SysMenu> menuList = list(lambdaQueryWrapper()
                .orderByAsc(SysMenu::getSortCode));
        List<SysMenuInfoResult> infoResultList = assembleMenuTree(menuList);
        SysMenuInfoResult infoResult = infoResultList.stream().filter(ir -> ir.getId().equals(id)).findFirst().orElse(null);
        CheckUtils.ifNull(infoResult, "删除的菜单不存在！");
        List<Long> deleteIds = flatInfoResultByParent(infoResult).stream().map(SysMenuInfoResult::getId).toList();
        this.removeBatchByIds(deleteIds);
    }

    /**
     * 扁平化菜单信息树
     *
     * @param parent 扁平化目标节点
     * @return 扁平化后菜单信息树 包括目标节点
     */
    private List<SysMenuInfoResult> flatInfoResultByParent(SysMenuInfoResult parent) {
        if (parent == null) {
            return Collections.emptyList();
        }
        List<SysMenuInfoResult> result = new ArrayList<>();
        result.add(parent);
        List<SysMenuInfoResult> children = parent.getChildren();
        if (CollUtil.isEmpty(children)) {
            return result;
        }
        for (SysMenuInfoResult child : children) {
            result.addAll(flatInfoResultByParent(child));
        }
        return result;
    }


}
