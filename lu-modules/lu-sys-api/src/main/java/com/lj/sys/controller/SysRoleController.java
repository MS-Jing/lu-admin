package com.lj.sys.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.sys.param.SysRolePageParam;
import com.lj.sys.param.SysRoleSaveParam;
import com.lj.sys.param.SysRoleUpdateParam;
import com.lj.sys.result.SysRoleInfoResult;
import com.lj.sys.result.SysRolePageResult;
import com.lj.sys.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author lj
 * @since 2024-12-10 17:21:50
 */
@RestController
@RequestMapping("/sys/sys_role")
@ResponseResultVo
@Tag(name = "系统角色表 管理")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    @GetMapping("/page")
    @SaCheckPermission("sys:sys_role:list")
    @Operation(summary = "系统角色表 分页查询")
    public IPage<SysRolePageResult> page(@ParameterObject SysRolePageParam param) {
        return sysRoleService.page(param);
    }

    @GetMapping("/info/{id}")
    @SaCheckPermission("sys:sys_role:info")
    @Operation(summary = "系统角色表 信息")
    public SysRoleInfoResult info(@PathVariable("id") @Parameter(name = "id", description = "系统角色表 id") Long id) {
        if (id == null) {
            return null;
        }
        return sysRoleService.info(id);
    }

    @PostMapping("/save")
    @SaCheckPermission("sys:sys_role:save")
    @Operation(summary = "系统角色表 新增")
    public void save(@RequestBody @Validated SysRoleSaveParam param) {
        sysRoleService.save(param);
    }

    @PostMapping("/update")
    @SaCheckPermission("sys:sys_role:update")
    @Operation(summary = "系统角色表 更新")
    public void update(@RequestBody @Validated SysRoleUpdateParam param) {
        sysRoleService.update(param);
    }

    @PostMapping("/delete/{id}")
    @SaCheckPermission("sys:sys_role:delete")
    @Operation(summary = "系统角色表 删除")
    public void delete(@PathVariable("id") @Parameter(name = "id", description = "系统角色表 id") Long id) {
        sysRoleService.delete(id);
    }

}
