package com.lj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author luojing
 * @since 2024/11/6 17:50
 * 排序方式枚举
 */
@AllArgsConstructor
@Getter
public enum SortordEnum implements ICommonEnum<String> {
    ASC("ASC", "升序"),

    DESC("DESC", "降序");

    private final String value;
    private final String desc;


}
