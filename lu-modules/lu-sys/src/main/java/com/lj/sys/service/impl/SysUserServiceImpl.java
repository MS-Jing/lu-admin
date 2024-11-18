package com.lj.sys.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.common.exception.CommonException;
import com.lj.mp.standard.StandardServiceImpl;
import com.lj.mp.utils.PageQueryUtils;
import com.lj.sys.params.SysUserPageParams;
import com.lj.sys.params.SysUserSaveParams;
import com.lj.sys.params.SysUserUpdateParams;
import com.lj.sys.entity.SysUser;
import com.lj.sys.enums.SysUserStatus;
import com.lj.sys.mapper.SysUserMapper;
import com.lj.sys.service.SysUserService;
import com.lj.sys.result.SysUserResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public IPage<SysUserResult> pageQuery(SysUserPageParams params) {
        return this.page(PageQueryUtils.getPage(params), lambdaQueryWrapper()
                        .like(StrUtil.isNotBlank(params.getNickName()), SysUser::getNickName, params.getNickName()))
                .convert(SysUserResult::of);
    }

    @Override
    public SysUserResult info(Long userId) {
        return SysUserResult.of(this.getById(userId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysUserSaveParams params) {
        if (params == null) {
            throw new CommonException("新增用户信息不能为空");
        }
        SysUser entity = params.toEntity();
        // 密码加密
        entity.setPassword(SaSecureUtil.sha256(params.getPassword()));
        // 设置状态为正常
        entity.setUserStatus(SysUserStatus.NORMAL);
        this.save(entity);
    }

    @Override
    public void update(SysUserUpdateParams params) {
        if (params == null) {
            throw new CommonException("更新用户信息不能为空");
        }
        SysUser entity = params.toEntity();
        this.updateById(entity);
    }
}
