package com.lj.sys.service.impl;

import com.lj.sys.entity.SysRole;
import com.lj.sys.mapper.SysRoleMapper;
import com.lj.sys.service.SysRoleService;
import com.lj.mp.standard.StandardServiceImpl;
import org.springframework.stereotype.Service;
import com.lj.mp.utils.PageQueryUtils;
import cn.hutool.core.util.ObjectUtil;
import com.lj.sys.result.SysRolePageResult;
import com.lj.sys.param.SysRoleSaveParam;
import com.lj.sys.result.SysRoleInfoResult;
import com.lj.sys.param.SysRolePageParam;
import com.lj.sys.param.SysRoleUpdateParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

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

    @Override
    public IPage<SysRolePageResult> page(SysRolePageParam param) {
        return this.page(PageQueryUtils.getPage(param), getQueryWrapper(param)).convert(SysRolePageResult::of);
    }

    private LambdaQueryWrapper<SysRole> getQueryWrapper(SysRolePageParam param) {
        return lambdaQueryWrapper()
                .eq(ObjectUtil.isNotEmpty(param.getRoleName()), SysRole::getRoleName, param.getRoleName())
                ;
    }

    @Override
    public SysRoleInfoResult info(Long id) {
        return SysRoleInfoResult.of(this.getById(id));
    }

    @Override
    public void save(SysRoleSaveParam param) {
        SysRole entity = param.toEntity();
        this.save(entity);
    }

    @Override
    public void update(SysRoleUpdateParam param) {
        SysRole entity = param.toEntity();
        this.updateById(entity);
    }
}