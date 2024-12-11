package com.lj.sys.param;

import com.lj.mp.utils.AbstractPageQueryParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单表 分页查询参数
 * </p>
 *
 * @author lj
 * @since 2024-12-11 14:36:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "菜单表 分页查询参数")
public class SysMenuPageParam extends AbstractPageQueryParams {

    @Schema(description = "菜单类型 参考字典: SysMenuType")
    private Integer menuType;

    @Schema(description = "菜单路由")
    private String path;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "菜单标题")
    private String title;
}