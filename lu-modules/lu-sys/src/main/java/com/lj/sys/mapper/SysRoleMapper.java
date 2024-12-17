package com.lj.sys.mapper;

import com.lj.mp.standard.StandardMapper;
import com.lj.sys.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


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

    /**
     * 获取用户拥有的角色
     *
     * @param userId 用户id
     * @return 用户拥有的角色列表
     */
    List<SysRole> getSysRoleByUserId(Long userId);
}