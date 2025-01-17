package com.lj.sys.enums;

import com.lj.mp.standard.IStandardEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author luojing
 * @since 2024/10/29 14:35
 */

@AllArgsConstructor
@Getter
public enum SysUserStatus implements IStandardEnum<Integer> {

    NORMAL(1, "正常"),
    LOCKING(2, "锁定中");


    private final Integer value;
    private final String desc;

}
