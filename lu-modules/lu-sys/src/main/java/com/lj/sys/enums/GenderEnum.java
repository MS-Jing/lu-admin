package com.lj.sys.enums;

import com.lj.common.enums.EnumDict;
import com.lj.mp.standard.IStandardEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author luojing
 * @since 2024/11/26 14:53
 */
@AllArgsConstructor
@Getter
@EnumDict(name = "Gender", description = "性别")
public enum GenderEnum implements IStandardEnum<Integer> {

    WOMAN(0, "女"),
    MAN(1, "男");


    private final Integer value;
    private final String desc;
}
