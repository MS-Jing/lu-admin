package com.lj.sys.params;

import com.lj.mp.utils.AbstractPageQueryParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author luojing
 * @since 2024/11/7 18:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统用户分页查询参数")
public class SysUserPageParams extends AbstractPageQueryParams {

    @Schema(description = "用户昵称")
    private String nickName;
}
