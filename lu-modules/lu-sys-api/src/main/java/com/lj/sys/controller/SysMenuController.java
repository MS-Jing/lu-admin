package com.lj.sys.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.sys.param.SysMenuSaveParam;
import com.lj.sys.param.SysMenuUpdateParam;
import com.lj.sys.result.SysMenuInfoResult;
import com.lj.sys.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author lj
 * @since 2024-12-11 14:36:37
 */
@RestController
@RequestMapping("/sys/sys_menu")
@ResponseResultVo
@Tag(name = "菜单表 管理")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    @GetMapping("/list")
    @SaCheckPermission("sys:sys_menu:list")
    @Operation(summary = "用户菜单列表", description = "获取用户拥有的菜单列表,渲染页面")
    public List<SysMenuInfoResult> listByUser() {
        return sysMenuService.listByUserId(StpUtil.getLoginIdAsLong());
    }

    @GetMapping("/button/permission")
    @SaCheckPermission("sys:sys_menu:list")
    @Operation(summary = "按钮权限", description = "当前用户页面拥有的按钮权限,key为页面路由的name,value为这个页面所有按钮的权限")
    public Map<String, List<String>> buttonPermission() {
        return sysMenuService.buttonPermission(StpUtil.getLoginIdAsLong());
    }

    @GetMapping("/tree/{menuType}")
    @SaCheckPermission("sys:sys_menu:tree")
    @Operation(summary = "菜单树", description = "主要用于总览菜单管理")
    public List<SysMenuInfoResult> tree(@PathVariable(value = "menuType", required = false) List<Integer> menuTypeList) {
        return sysMenuService.tree(menuTypeList);
    }

    @GetMapping("/info/{id}")
    @SaCheckPermission("sys:sys_menu:info")
    @Operation(summary = "菜单表 信息")
    public SysMenuInfoResult info(@PathVariable("id") @Parameter(name = "id", description = "菜单表 id") Long id) {
        if (id == null) {
            return null;
        }
        return sysMenuService.info(id);
    }

    @PostMapping("/save")
    @SaCheckPermission("sys:sys_menu:save")
    @Operation(summary = "菜单表 新增")
    public void save(@RequestBody @Validated SysMenuSaveParam param) {
        sysMenuService.save(param);
    }

    @PostMapping("/update")
    @SaCheckPermission("sys:sys_menu:update")
    @Operation(summary = "菜单表 更新")
    public void update(@RequestBody @Validated SysMenuUpdateParam param) {
        sysMenuService.update(param);
    }

    @PostMapping("/delete")
    @SaCheckPermission("sys:sys_menu:delete")
    @Operation(summary = "菜单表 删除")
    public void delete(@RequestBody List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        sysMenuService.removeByIds(ids);
    }

}
