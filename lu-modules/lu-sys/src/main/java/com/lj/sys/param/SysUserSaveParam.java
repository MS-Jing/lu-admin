package com.lj.sys.param;

import com.lj.sys.entity.SysUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.lj.sys.enums.SysUserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 系统用户 保存参数
 * </p>
 *
 * @author luojing
 * @since 2024-12-10 14:49:26
 */
@Data
@Schema(description = "系统用户 保存参数")
public class SysUserSaveParam {

    @Schema(description = "用户昵称 ")
    @NotBlank(message = "用户昵称 不能为空")
    private String nickName;

    @Schema(description = "用户名 ")
    @NotBlank(message = "用户名 不能为空")
    private String userName;

    @Schema(description = "密码 ")
    private String password;

    @Schema(description = "电子邮箱 ")
    private String email;

    @Schema(description = "移动电话 ")
    private String mobile;

    @Schema(description = "用户状态 参考字典: SysUserStatus")
    @NotNull(message = "用户状态 不能为空")
    private Integer userStatus;

    public SysUser toEntity() {
        SysUser entity = new SysUser();
        entity.setNickName(nickName);
        entity.setUserName(userName);
        entity.setPassword(password);
        entity.setEmail(email);
        entity.setMobile(mobile);
        entity.setUserStatus(SysUserStatus.ofByValue(userStatus));
        return entity;
    }
}