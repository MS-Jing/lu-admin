package com.lj.sys.user.mapper;

import com.lj.mp.standard.StandardMapper;
import com.lj.sys.user.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author lj
 * @since 2024-08-09
 */

@Repository
public interface SysUserMapper extends StandardMapper<SysUser> {

}
