package com.lj.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.mp.standard.StandardService;
import com.lj.sys.entity.SysUser;
import com.lj.sys.param.SysUserPageParam;
import com.lj.sys.param.SysUserSaveParam;
import com.lj.sys.param.SysUserUpdateParam;
import com.lj.sys.result.SysUserInfoResult;
import com.lj.sys.result.SysUserPageResult;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author lj
 * @since 2024-08-09
 */
public interface SysUserService extends StandardService<SysUser> {

    default SysUser getUserByUserName(String userName) {
        return getOne(lambdaQueryWrapper().eq(SysUser::getUserName, userName));
    }

    IPage<SysUserPageResult> page(SysUserPageParam param);

    SysUserInfoResult info(Long id);

    void save(SysUserSaveParam param);

    void update(SysUserUpdateParam param);

}
