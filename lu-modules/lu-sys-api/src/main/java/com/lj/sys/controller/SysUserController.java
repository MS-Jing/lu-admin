package com.lj.sys.controller;

import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.sys.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author lj
 * @since 2024-08-09
 */
@RestController
@ResponseResultVo
@RequestMapping("/sys/user")
@Api("用户管理")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


}
