package com.lj.sys.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.common.exception.CommonException;
import com.lj.mp.standard.StandardServiceImpl;
import com.lj.mp.utils.PageQueryUtils;
import com.lj.sys.entity.SysUser;
import com.lj.sys.enums.SysUserStatus;
import com.lj.sys.mapper.SysUserMapper;
import com.lj.sys.param.SysUserPageParam;
import com.lj.sys.param.SysUserSaveParam;
import com.lj.sys.param.SysUserUpdateParam;
import com.lj.sys.result.SysUserInfoResult;
import com.lj.sys.result.SysUserPageResult;
import com.lj.sys.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author lj
 * @since 2024-08-09
 */
@Service
public class SysUserServiceImpl extends StandardServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public IPage<SysUserPageResult> page(SysUserPageParam param) {
        return this.page(PageQueryUtils.getPage(param), getQueryWrapper(param)).convert(SysUserPageResult::of);
    }

    private LambdaQueryWrapper<SysUser> getQueryWrapper(SysUserPageParam param) {
        return lambdaQueryWrapper()
                .like(ObjectUtil.isNotEmpty(param.getNickName()), SysUser::getNickName, param.getNickName())
                .eq(ObjectUtil.isNotEmpty(param.getMobile()), SysUser::getMobile, param.getMobile())
                .eq(ObjectUtil.isNotEmpty(param.getUserStatus()), SysUser::getUserStatus, param.getUserStatus())
                ;
    }

    @Override
    public SysUserInfoResult info(Long id) {
        return SysUserInfoResult.of(this.getById(id));
    }

    @Override
    public void save(SysUserSaveParam param) {
        if (param == null) {
            throw new CommonException("新增用户信息不能为空");
        }
        SysUser entity = param.toEntity();
        // 密码加密
        entity.setPassword(SaSecureUtil.sha256(param.getPassword()));
        // 设置状态为正常
        entity.setUserStatus(SysUserStatus.NORMAL);
        this.save(entity);
    }

    @Override
    public void update(SysUserUpdateParam param) {
        if (param == null) {
            throw new CommonException("更新用户信息不能为空");
        }
        SysUser entity = param.toEntity();
        this.updateById(entity);
    }
}
