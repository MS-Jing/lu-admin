package com.lj.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.mp.standard.StandardService;
import com.lj.sys.dto.SysUserPageDto;
import com.lj.sys.entity.SysUser;
import com.lj.sys.vo.SysUserVo;

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

    IPage<SysUserVo> pageQuery(SysUserPageDto dto);

    SysUserVo info(Long userId);
}
