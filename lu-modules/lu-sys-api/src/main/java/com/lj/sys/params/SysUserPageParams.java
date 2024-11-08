package com.lj.sys.params;

import com.lj.mp.utils.AbstractPageQueryParams;
import com.lj.sys.dto.SysUserPageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author luojing
 * @since 2024/11/7 18:17
 * 系统用户分页查询参数
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("系统用户分页查询参数")
public class SysUserPageParams extends AbstractPageQueryParams {

    @ApiModelProperty("用户昵称")
    private String nickName;

    public SysUserPageDto toDto() {
        SysUserPageDto sysUserPageDto = new SysUserPageDto();
        sysUserPageDto.copy(this);
        sysUserPageDto.setNickName(nickName);
        return sysUserPageDto;
    }

}
