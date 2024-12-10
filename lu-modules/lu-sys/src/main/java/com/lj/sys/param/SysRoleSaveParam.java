package com.lj.sys.param;

import com.lj.sys.entity.SysRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 系统角色表 保存参数
 * </p>
 *
 * @author lj
 * @since 2024-12-10 17:21:50
 */
@Data
@Schema(description = "系统角色表 保存参数")
public class SysRoleSaveParam {

    @Schema(description = "角色名称 ")
    private String roleName;

    @Schema(description = "备注 ")
    private String remark;

    public SysRole toEntity() {
        SysRole entity = new SysRole();
        entity.setRoleName(roleName);
        entity.setRemark(remark);
        return entity;
    }
}