package com.lj.sys.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.sys.params.SysUserPageParams;
import com.lj.sys.result.SysUserResult;
import com.lj.sys.service.SysUserService;
import com.lj.sys.vo.SysUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(tags = "用户管理")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/pageQuery")
    @SaCheckPermission("sys:user:list")
    @ApiOperation("用户分页查询")
    public IPage<SysUserResult> pageQuery(SysUserPageParams params) {
        IPage<SysUserVo> page = sysUserService.pageQuery(params.toDto());
        return page.convert(SysUserResult::of);
    }

}
