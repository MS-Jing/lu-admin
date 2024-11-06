package com.lj.sys.service;

import com.lj.mp.standard.StandardService;
import com.lj.sys.entity.SysUser;

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
}
