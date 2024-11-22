package com.lj.sys.enums;

import com.lj.common.exception.CommonException;
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

    public static SysUserStatus ofByValue(Integer value) {
        SysUserStatus[] values = SysUserStatus.values();
        for (SysUserStatus val : values) {
            if (val.getValue().equals(value)) {
                return val;
            }
        }
        throw new CommonException("错误的枚举值: " + value);
    }

}
