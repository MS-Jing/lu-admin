package com.lj.sys.params;

import com.lj.sys.entity.dto.SysUserSaveDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author luojing
 * @since 2024/11/11 10:52
 */
@Data
@Schema(description = "系统用户保存参数")
public class SysUserSaveParams {

    @Schema(description = "用户昵称")
    @NotBlank(message = "用户昵称不能为空")
    private String nickName;

    @Schema(description = "用户名(用于登录的)")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "移动电话")
    private String mobile;

    public SysUserSaveDto toDto() {
        SysUserSaveDto sysUserSaveDto = new SysUserSaveDto();
        sysUserSaveDto.setNickName(nickName);
        sysUserSaveDto.setUserName(userName);
        sysUserSaveDto.setPassword(password);
        sysUserSaveDto.setEmail(email);
        sysUserSaveDto.setMobile(mobile);
        return sysUserSaveDto;
    }
}
