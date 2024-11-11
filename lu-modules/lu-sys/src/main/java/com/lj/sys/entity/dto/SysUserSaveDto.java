package com.lj.sys.entity.dto;

import com.lj.sys.entity.SysUser;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author luojing
 * @since 2024/11/11 11:03
 */
@Data
public class SysUserSaveDto {
    @NotBlank(message = "用户昵称不能为空")
    private String nickName;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String email;

    private String mobile;

    public SysUser toEntity() {
        SysUser sysUser = new SysUser();
        sysUser.setNickName(nickName);
        sysUser.setUserName(userName);
        sysUser.setPassword(password);
        sysUser.setEmail(email);
        sysUser.setMobile(mobile);
        return sysUser;
    }

}
