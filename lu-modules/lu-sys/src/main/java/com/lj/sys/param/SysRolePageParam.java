package com.lj.sys.param;

import com.lj.mp.utils.AbstractPageQueryParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统角色表 分页查询参数
 * </p>
 *
 * @author lj
 * @since 2024-12-10 17:21:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统角色表 分页查询参数")
public class SysRolePageParam extends AbstractPageQueryParams {

    @Schema(description = "角色名称 ")
    private String roleName;
}