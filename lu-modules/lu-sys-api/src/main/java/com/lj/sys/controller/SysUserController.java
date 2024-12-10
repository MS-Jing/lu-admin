package com.lj.sys.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.common.exception.CommonException;
import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.sys.constant.SysConstant;
import com.lj.sys.param.SysUserPageParam;
import com.lj.sys.param.SysUserSaveParam;
import com.lj.sys.param.SysUserUpdateParam;
import com.lj.sys.result.SysUserInfoResult;
import com.lj.sys.result.SysUserPageResult;
import com.lj.sys.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springdoc.core.annotations.ParameterObject;
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

    @Resource
    private SysUserService sysUserService;

    @GetMapping("/page")
    @SaCheckPermission("sys:sys_user:list")
    @Operation(summary = "系统用户 分页查询")
    public IPage<SysUserPageResult> page(@ParameterObject SysUserPageParam param) {
        return sysUserService.page(param);
    }

    @GetMapping("/info")
    @Operation(summary = "用户个人信息")
    public SysUserInfoResult info() {
        Long userId = StpUtil.getLoginIdAsLong();
        return sysUserService.info(userId);
    }


    @GetMapping("/info/{id}")
    @SaCheckPermission("sys:sys_user:info")
    @Operation(summary = "系统用户 信息")
    public SysUserInfoResult info(@PathVariable("id") @Parameter(name = "id", description = "系统用户 id") Long id) {
        if (id == null) {
            return null;
        }
        return sysUserService.info(id);
    }

    @PostMapping("/save")
    @SaCheckPermission("sys:sys_user:save")
    @Operation(summary = "系统用户 新增")
    public void save(@RequestBody @Validated SysUserSaveParam param) {
        sysUserService.save(param);
    }

    @PostMapping("/update")
    @SaCheckPermission("sys:sys_user:update")
    @Operation(summary = "系统用户 更新")
    public void update(@RequestBody @Validated SysUserUpdateParam param) {
        sysUserService.update(param);
    }

    @PostMapping("/delete")
    @SaCheckPermission("sys:sys_user:delete")
    @Operation(summary = "系统用户 删除")
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
