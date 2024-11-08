package com.lj.sys.result;

import com.lj.sys.vo.SysUserVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author luojing
 * @since 2024/11/7 18:34
 */
@Data
public class SysUserResult {
    private Long id;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("移动电话")
    private String mobile;

    @ApiModelProperty("用户状态")
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
