package com.lj.sys.role.controller;

import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.sys.role.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author lj
 * @since 2024-08-12
 */
@RestController
@ResponseResultVo
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

}
