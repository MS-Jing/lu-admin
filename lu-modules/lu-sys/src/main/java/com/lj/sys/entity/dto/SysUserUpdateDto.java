package com.lj.sys.entity.dto;

import com.lj.sys.entity.SysUser;
import lombok.Data;

/**
 * @author luojing
 * @since 2024/11/11 14:31
 */
@Data
public class SysUserUpdateDto {

    private Long id;

    private String nickName;

    private String email;

    private String mobile;

    public SysUser toEntity() {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setNickName(nickName);
        sysUser.setEmail(email);
        sysUser.setMobile(mobile);
        return sysUser;
    }
}
