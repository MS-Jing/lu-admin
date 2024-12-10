package com.lj.sys.mapper;

import com.lj.sys.entity.SysUserRole;
import com.lj.mp.standard.StandardMapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author lj
 * @since 2024-12-10 17:08:40
 */
@Repository
@Mapper
public interface SysUserRoleMapper extends StandardMapper<SysUserRole> {

}