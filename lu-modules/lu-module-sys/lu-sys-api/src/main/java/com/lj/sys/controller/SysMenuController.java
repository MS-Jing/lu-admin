package com.lj.sys.controller;

import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.sys.menu.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author lj
 * @since 2024-08-13
 */
@RestController
@ResponseResultVo
@RequestMapping("/menu/sys-menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

}
