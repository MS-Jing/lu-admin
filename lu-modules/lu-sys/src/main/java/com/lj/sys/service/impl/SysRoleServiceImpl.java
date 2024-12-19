package com.lj.sys.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lj.common.exception.CommonException;
import com.lj.mp.standard.StandardServiceImpl;
import com.lj.mp.utils.PageQueryUtils;
import com.lj.sys.constant.SysConstant;
import com.lj.sys.entity.SysRole;
import com.lj.sys.entity.SysRoleMenu;
import com.lj.sys.mapper.SysRoleMapper;
import com.lj.sys.param.SysRolePageParam;
import com.lj.sys.param.SysRoleSaveParam;
import com.lj.sys.param.SysRoleUpdateParam;
import com.lj.sys.result.SysRoleInfoResult;
import com.lj.sys.result.SysRolePageResult;
import com.lj.sys.service.SysRoleMenuService;
import com.lj.sys.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author lj
 * @since 2024-12-10 17:21:50
 */
@Service
public class SysRoleServiceImpl extends StandardServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public IPage<SysRolePageResult> page(SysRolePageParam param) {
        // 超级管理员有上帝视角，拥有所有角色
        Long userId = StpUtil.getLoginIdAsLong();
        Page<SysRole> page = PageQueryUtils.getPage(param);
        if (SysConstant.SUPER_ADMIN_ID.equals(userId)) {
            return this.page(page, getQueryWrapper(param))
                    .convert(SysRolePageResult::of);
        }
        // 非超级管理员只能看到自己拥有的权限
        return baseMapper.getSysRoleByUserIdPage(page, userId, getQueryWrapper(param))
                .convert(SysRolePageResult::of);
    }

    private LambdaQueryWrapper<SysRole> getQueryWrapper(SysRolePageParam param) {
        return lambdaQueryWrapper()
                .like(ObjectUtil.isNotEmpty(param.getRoleName()), SysRole::getRoleName, param.getRoleName())
                ;
    }

    @Override
    public SysRoleInfoResult info(Long id) {
        SysRoleInfoResult sysRoleInfoResult = SysRoleInfoResult.of(this.getById(id));
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuService.getByRoleId(id);
        sysRoleInfoResult.setMenuIdList(sysRoleMenuList.stream().map(SysRoleMenu::getMenuId).toList());
        return sysRoleInfoResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysRoleSaveParam param) {
        SysRole entity = param.toEntity();
        try {
            this.save(entity);
        } catch (DuplicateKeyException e) {
            throw new CommonException("角色编码:" + param.getRoleCode() + " 被占用! 请联系管理员!");
        }
        sysRoleMenuService.refresh(entity.getId(), param.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleUpdateParam param) {
        SysRole entity = param.toEntity();
        try {
            this.updateById(entity);
        } catch (DuplicateKeyException e) {
            throw new CommonException("角色编码:" + param.getRoleCode() + " 被占用! 请联系管理员!");
        }
        sysRoleMenuService.refresh(entity.getId(), param.getMenuIdList());
    }

    @Override
    public List<SysRole> getSysRoleByUserId(Long userId) {
        // 超级管理员有上帝视角，拥有所有角色
        if (SysConstant.SUPER_ADMIN_ID.equals(userId)) {
            return list();
        }
        return baseMapper.getSysRoleByUserId(userId);
    }
}
