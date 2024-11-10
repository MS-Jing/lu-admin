package com.lj.sys.controller;

import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.sys.service.SysRoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "角色管理")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

}
