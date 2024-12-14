package com.lj.sys.param;

import com.lj.common.utils.EnumUtils;
import com.lj.sys.entity.SysUser;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import com.lj.sys.enums.SysUserStatus;

/**
 * <p>
 * 系统用户 更新参数
 * </p>
 *
 * @author luojing
 * @since 2024-12-10 14:49:26
 */
@Data
@Schema(description = "系统用户 更新参数")
public class SysUserUpdateParam {

    @NotNull(message = "id不能为空")
    @Schema(description = "id")
    private Long id;

    @Schema(description = "用户昵称 ")
    private String nickName;

    @Schema(description = "用户名 ")
    private String userName;

    @Schema(description = "电子邮箱 ")
    private String email;

    @Schema(description = "移动电话 ")
    private String mobile;

    @Schema(description = "用户状态 参考字典: SysUserStatus")
    private Integer userStatus;

    public SysUser toEntity() {
        SysUser entity = new SysUser();
        entity.setId(id);
        entity.setNickName(nickName);
        entity.setUserName(userName);
        entity.setEmail(email);
        entity.setMobile(mobile);
        entity.setUserStatus(EnumUtils.getByValue(SysUserStatus.class, userStatus));
        return entity;
    }
}