package com.lj.sys.result;

import com.lj.sys.entity.SysRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 系统角色表 信息结果
 * </p>
 *
 * @author lj
 * @since 2024-12-10 17:21:50
 */
@Data
@Schema(description = "系统角色表 信息结果")
public class SysRoleInfoResult {

    private Long id;

    @Schema(description = "角色编码")
    private String roleCode;

    @Schema(description = "角色名称 ")
    private String roleName;

    @Schema(description = "备注 ")
    private String remark;

    public static SysRoleInfoResult of(SysRole entity) {
        if (entity == null) {
            return null;
        }
        SysRoleInfoResult result = new SysRoleInfoResult();
        result.setId(entity.getId());
        result.setRoleCode(entity.getRoleCode());
        result.setRoleName(entity.getRoleName());
        result.setRemark(entity.getRemark());
        return result;
    }
}