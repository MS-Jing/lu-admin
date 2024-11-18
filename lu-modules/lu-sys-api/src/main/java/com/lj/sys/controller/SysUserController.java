package com.lj.sys.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.common.exception.CommonException;
import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.sys.constant.SysConstant;
import com.lj.sys.params.SysUserPageParams;
import com.lj.sys.params.SysUserSaveParams;
import com.lj.sys.params.SysUserUpdateParams;
import com.lj.sys.result.SysUserResult;
import com.lj.sys.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return sysUserService.pageQuery(params);
    }

    @GetMapping("/info")
    @Operation(summary = "用户个人信息")
    public SysUserResult info() {
        Long userId = StpUtil.getLoginIdAsLong();
        return sysUserService.info(userId);
    }

    @GetMapping("/info/{userId}")
    @SaCheckPermission("sys:user:info")
    @Operation(summary = "用户信息")
    public SysUserResult info(@PathVariable("userId") @Parameter(name = "userId", description = "用户id") Long userId) {
        if (userId == null) {
            return null;
        }
        return sysUserService.info(userId);
    }

    @PostMapping("/save")
    @SaCheckPermission("sys:user:save")
    @Operation(summary = "用户新增")
    public void save(@RequestBody @Validated SysUserSaveParams params) {
        sysUserService.save(params);
    }

    @PostMapping("/update")
    @SaCheckPermission("sys:user:update")
    @Operation(summary = "用户更新")
    public void update(@RequestBody @Validated SysUserUpdateParams params) {
        sysUserService.update(params);
    }

    @PostMapping("/delete")
    @SaCheckPermission("sys:user:delete")
    @Operation(summary = "删除用户")
    public void delete(@RequestBody List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        if (CollUtil.contains(ids, SysConstant.SUPER_ADMIN_ID)) {
            // 超级管理员无法被删除
            throw new CommonException("超级管理员无法删除!");
        }
        sysUserService.removeByIds(ids);
    }

}
