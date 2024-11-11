package com.lj.sys.params;

import com.lj.sys.dto.SysUserUpdateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author luojing
 * @since 2024/11/11 10:52
 */
@Data
@Schema(description = "系统用户更新参数")
public class SysUserUpdateParams {

    @Schema(description = "用户id")
    @NotNull(message = "用户id不能为空")
    private Long userId;

    @Schema(description = "用户昵称")
    private String nickName;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "移动电话")
    private String mobile;

    public SysUserUpdateDto toDto() {
        SysUserUpdateDto sysUserUpdateDto = new SysUserUpdateDto();
        sysUserUpdateDto.setId(userId);
        sysUserUpdateDto.setNickName(nickName);
        sysUserUpdateDto.setEmail(email);
        sysUserUpdateDto.setMobile(mobile);
        return sysUserUpdateDto;
    }
}
