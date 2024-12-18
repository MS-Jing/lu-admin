package com.lj.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lj.mp.standard.StandardMapper;
import com.lj.sys.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    default List<SysRole> getSysRoleByUserId(Long userId) {
        return getSysRoleByUserId(userId, Wrappers.emptyWrapper());
    }

    /**
     * 获取用户拥有的角色
     *
     * @param userId       用户id
     * @param queryWrapper 实体对象封装操作类
     * @return 用户拥有的角色列表
     */
    List<SysRole> getSysRoleByUserId(Long userId, @Param(Constants.WRAPPER) Wrapper<SysRole> queryWrapper);

    /**
     * 分页获取用户拥有的角色
     *
     * @param page         分页对象
     * @param userId       用户id
     * @param queryWrapper 实体对象封装操作类
     * @return 用户拥有的角色分页
     */
    Page<SysRole> getSysRoleByUserIdPage(Page<SysRole> page, @Param("userId") Long userId, @Param(Constants.WRAPPER) Wrapper<SysRole> queryWrapper);
}
