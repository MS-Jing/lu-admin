package com.lj.sys.result;

import com.lj.sys.entity.SysUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 系统用户 分页结果
 * </p>
 *
 * @author luojing
 * @since 2024-12-10 14:49:26
 */
@Data
@Schema(description = "系统用户 分页结果")
public class SysUserPageResult {

    private Long id;

    @Schema(description = "用户昵称 ")
    private String nickName;

    @Schema(description = "移动电话 ")
    private String mobile;

    @Schema(description = "用户状态 参考字典: SysUserStatus")
    private Integer userStatus;

    public static SysUserPageResult of(SysUser entity) {
        if (entity == null) {
            return null;
        }
        SysUserPageResult result = new SysUserPageResult();
        result.setId(entity.getId());
        result.setNickName(entity.getNickName());
        result.setMobile(entity.getMobile());
        result.setUserStatus(entity.getUserStatus().getValue());
        return result;
    }
}