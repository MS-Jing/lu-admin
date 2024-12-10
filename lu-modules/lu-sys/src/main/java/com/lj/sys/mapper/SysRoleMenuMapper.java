package com.lj.sys.mapper;

import com.lj.sys.entity.SysRoleMenu;
import com.lj.mp.standard.StandardMapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * 角色菜单关联表 Mapper 接口
 * </p>
 *
 * @author lj
 * @since 2024-12-10 17:27:54
 */
@Repository
@Mapper
public interface SysRoleMenuMapper extends StandardMapper<SysRoleMenu> {

}
