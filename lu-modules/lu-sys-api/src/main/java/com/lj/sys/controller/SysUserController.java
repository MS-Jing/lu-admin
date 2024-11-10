package com.lj.sys.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.sys.params.SysUserPageParams;
import com.lj.sys.result.SysUserResult;
import com.lj.sys.service.SysUserService;
import com.lj.sys.vo.SysUserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@Tag(name = "用户管理")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/pageQuery")
    @SaCheckPermission("sys:user:list")
    @Operation(summary = "用户分页查询")
    public IPage<SysUserResult> pageQuery(@ParameterObject SysUserPageParams params) {
        IPage<SysUserVo> page = sysUserService.pageQuery(params.toDto());
        return page.convert(SysUserResult::of);
    }

    @GetMapping("/info")
    @Operation(summary = "用户个人信息")
    public SysUserResult info() {
        Long userId = StpUtil.getLoginIdAsLong();
        return SysUserResult.of(sysUserService.info(userId));
    }

    @GetMapping("/info/{userId}")
    @SaCheckPermission("sys:user:info")
    @Operation(summary = "用户信息")
    public SysUserResult info(@PathVariable("userId") @Parameter(name = "userId", description = "用户id") Long userId) {
        if (userId == null) {
            return null;
        }
        return SysUserResult.of(sysUserService.info(userId));
    }


}
