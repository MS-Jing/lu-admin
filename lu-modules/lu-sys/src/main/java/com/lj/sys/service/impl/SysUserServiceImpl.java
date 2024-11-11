package com.lj.sys.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.common.exception.CommonException;
import com.lj.mp.standard.StandardServiceImpl;
import com.lj.mp.utils.PageQueryUtils;
import com.lj.sys.dto.SysUserPageDto;
import com.lj.sys.dto.SysUserSaveDto;
import com.lj.sys.dto.SysUserUpdateDto;
import com.lj.sys.entity.SysUser;
import com.lj.sys.enums.SysUserStatus;
import com.lj.sys.mapper.SysUserMapper;
import com.lj.sys.service.SysUserService;
import com.lj.sys.vo.SysUserVo;
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
    public IPage<SysUserVo> pageQuery(SysUserPageDto dto) {
        return this.page(PageQueryUtils.getPage(dto), lambdaQueryWrapper()
                        .like(StrUtil.isNotBlank(dto.getNickName()), SysUser::getNickName, dto.getNickName()))
                .convert(sysUser -> BeanUtil.toBean(sysUser, SysUserVo.class));
    }

    @Override
    public SysUserVo info(Long userId) {
        SysUser sysUser = this.getById(userId);
        if (sysUser == null) {
            return null;
        }
        return BeanUtil.toBean(sysUser, SysUserVo.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysUserSaveDto saveDto) {
        if (saveDto == null) {
            throw new CommonException("新增用户信息不能为空");
        }
        SysUser entity = saveDto.toEntity();
        // 密码加密
        entity.setPassword(SaSecureUtil.sha256(saveDto.getPassword()));
        // 设置状态为正常
        entity.setUserStatus(SysUserStatus.NORMAL);
        this.save(entity);
    }

    @Override
    public void update(SysUserUpdateDto updateDto) {
        if (updateDto == null) {
            throw new CommonException("更新用户信息不能为空");
        }
        SysUser entity = updateDto.toEntity();
        this.updateById(entity);
    }
}
