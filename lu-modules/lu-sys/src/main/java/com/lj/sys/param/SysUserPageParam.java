package com.lj.sys.param;

import com.lj.mp.utils.AbstractPageQueryParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统用户 分页查询参数
 * </p>
 *
 * @author luojing
 * @since 2024-12-10 14:49:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统用户 分页查询参数")
public class SysUserPageParam extends AbstractPageQueryParams {

    @Schema(description = "用户昵称 ")
    private String nickName;

    @Schema(description = "移动电话 ")
    private String mobile;

    @Schema(description = "用户状态 参考字典: SysUserStatus")
    private Integer userStatus;
}