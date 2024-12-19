package com.lj.sys.result;

import com.lj.sys.entity.SysRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统角色表 分页结果
 * </p>
 *
 * @author lj
 * @since 2024-12-10 17:21:50
 */
@Data
@Schema(description = "系统角色表 分页结果")
public class SysRolePageResult {

    private Long id;

    @Schema(description = "角色编码")
    private String roleCode;

    @Schema(description = "角色名称 ")
    private String roleName;

    @Schema(description = "备注 ")
    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public static SysRolePageResult of(SysRole entity) {
        if (entity == null) {
            return null;
        }
        SysRolePageResult result = new SysRolePageResult();
        result.setId(entity.getId());
        result.setRoleCode(entity.getRoleCode());
        result.setRoleName(entity.getRoleName());
        result.setRemark(entity.getRemark());
        result.setCreateTime(entity.getCreateTime());
        result.setUpdateTime(entity.getUpdateTime());
        return result;
    }
}