package com.lj.sys.mapper;

import com.lj.sys.entity.SysUser;
import com.lj.mp.standard.StandardMapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author luojing
 * @since 2024-12-10 14:49:26
 */
@Repository
@Mapper
public interface SysUserMapper extends StandardMapper<SysUser> {

}