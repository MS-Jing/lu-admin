package com.lj.sys.mapper;

import com.lj.mp.standard.StandardMapper;
import com.lj.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author lj
 * @since 2024-12-11 14:36:37
 */
@Repository
@Mapper
public interface SysMenuMapper extends StandardMapper<SysMenu> {

    /**
     * 获取关于这个用户的所有菜单
     *
     * @param userId     用户id
     * @param moduleNameSet 模块名称
     * @return 这个用户的所有菜单
     */
    List<SysMenu> listByUserId(Long userId, Set<String> moduleNameSet);

    List<SysMenu> buttonByUserId(Long userId, Set<String> moduleNameSet);

}
