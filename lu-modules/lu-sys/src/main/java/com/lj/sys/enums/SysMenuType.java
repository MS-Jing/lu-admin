package com.lj.sys.enums;

import com.lj.common.enums.EnumDict;
import com.lj.mp.standard.IStandardEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author luojing
 * @since 2024/12/11 14:23
 */
@AllArgsConstructor
@Getter
@EnumDict(name = "SysMenuType", description = "系统菜单类型")
public enum SysMenuType implements IStandardEnum<Integer> {

    DIR(1, "目录"),
    MENU(2, "菜单"),
    BUTTON(3, "按钮");

    private final Integer value;
    private final String desc;

}
