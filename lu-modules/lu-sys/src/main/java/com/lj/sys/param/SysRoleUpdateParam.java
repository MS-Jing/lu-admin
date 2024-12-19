package com.lj.sys.param;

import com.lj.sys.entity.SysRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

/**
 * <p>
 * 系统角色表 更新参数
 * </p>
 *
 * @author lj
 * @since 2024-12-10 17:21:50
 */
@Data
@Schema(description = "系统角色表 更新参数")
public class SysRoleUpdateParam {

    private Long id;

    @Schema(description = "角色编码")
    private String roleCode;

    @Schema(description = "角色名称 ")
    private String roleName;

    @Schema(description = "角色拥有的菜单权限")
    private Set<Long> menuIdList;

    @Schema(description = "备注 ")
    private String remark;

    public SysRole toEntity() {
        SysRole entity = new SysRole();
        entity.setId(id);
        entity.setRoleCode(roleCode);
        entity.setRoleName(roleName);
        entity.setRemark(remark);
        return entity;
    }
}