package com.lj.sys.mapper;

import com.lj.sys.entity.SysRole;
import com.lj.mp.standard.StandardMapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author lj
 * @since 2024-12-10 17:21:50
 */
@Repository
@Mapper
public interface SysRoleMapper extends StandardMapper<SysRole> {

}