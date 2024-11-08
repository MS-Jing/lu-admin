package com.lj.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lj.mp.standard.StandardServiceImpl;
import com.lj.mp.utils.PageQueryUtils;
import com.lj.sys.dto.SysUserPageDto;
import com.lj.sys.entity.SysUser;
import com.lj.sys.mapper.SysUserMapper;
import com.lj.sys.service.SysUserService;
import com.lj.sys.vo.SysUserVo;
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
    public IPage<SysUserVo> pageQuery(SysUserPageDto dto) {
        return this.page(PageQueryUtils.getPage(dto), lambdaQueryWrapper()
                        .like(StrUtil.isNotBlank(dto.getNickName()), SysUser::getNickName, dto.getNickName()))
                .convert(sysUser -> BeanUtil.toBean(sysUser, SysUserVo.class));
    }

    @Override
    public SysUserVo info(Long userId) {
        SysUser sysUser = this.getById(userId);
        return BeanUtil.toBean(sysUser, SysUserVo.class);
    }
}
