package com.lj.sys.dto;

import com.lj.mp.utils.AbstractPageQueryParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author luojing
 * @since 2024/11/7 18:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserPageDto extends AbstractPageQueryParams {

    private String nickName;
}
