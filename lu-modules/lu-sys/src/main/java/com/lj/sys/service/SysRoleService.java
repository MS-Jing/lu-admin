package com.lj.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.mp.standard.StandardService;
import com.lj.sys.entity.SysRole;
import com.lj.sys.param.SysRolePageParam;
import com.lj.sys.param.SysRoleSaveParam;
import com.lj.sys.param.SysRoleUpdateParam;
import com.lj.sys.result.SysRoleInfoResult;
import com.lj.sys.result.SysRolePageResult;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author lj
 * @since 2024-12-10 17:21:50
 */
public interface SysRoleService extends StandardService<SysRole> {

    IPage<SysRolePageResult> page(SysRolePageParam param);

    SysRoleInfoResult info(Long id);

    void save(SysRoleSaveParam param);

    void update(SysRoleUpdateParam param);

    /**
     * 获取用户拥有的角色
     *
     * @param userId 用户id
     * @return 用户拥有的角色列表
     */
    List<SysRole> getSysRoleByUserId(Long userId);


    /**
     * 根据id删除角色
     *
     * @param id 角色id
     */
    void delete(Long id);
}