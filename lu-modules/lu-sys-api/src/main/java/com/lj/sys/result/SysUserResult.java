package com.lj.sys.result;

import com.lj.sys.entity.vo.SysUserVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author luojing
 * @since 2024/11/7 18:34
 */
@Data
@Schema(description = "系统用户信息")
public class SysUserResult {
    private Long id;

    @Schema(description = "用户昵称")
    private String nickName;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "移动电话")
    private String mobile;

    @Schema(description = "用户状态")
    private Integer userStatus;

    public static SysUserResult of(SysUserVo sysUserVo) {
        if (sysUserVo == null) {
            return null;
        }
        SysUserResult sysUserResult = new SysUserResult();
        sysUserResult.setId(sysUserVo.getId());
        sysUserResult.setNickName(sysUserVo.getNickName());
        sysUserResult.setUserName(sysUserVo.getUserName());
        sysUserResult.setEmail(sysUserVo.getEmail());
        sysUserResult.setMobile(sysUserVo.getMobile());
        sysUserResult.setUserStatus(sysUserVo.getUserStatus().getValue());
        return sysUserResult;
    }
}
