package com.lj.common.utils;

import cn.hutool.core.lang.func.Func1;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.ObjUtil;
import com.lj.common.enums.ICommonEnum;

/**
 * @author luojing
 * @since 2024/12/14 14:25
 * 枚举工具类 扩展了hutool的工具类
 */
public class EnumUtils extends EnumUtil {

    /**
     * 通过 某字段对应值 获取 枚举，获取不到时为 {@code null}
     *
     * @param enumClass 枚举类
     * @param condition 条件字段，为{@code null}返回{@code null}
     * @param value     条件字段值
     * @param <E>       枚举类型
     * @param <C>       字段类型
     * @return 对应枚举 ，获取不到时为 {@code null}
     */
    public static <E extends Enum<E>, C> E getBy(Class<E> enumClass, Func1<E, C> condition, C value) {
        if (null == enumClass || null == condition) {
            return null;
        }
        return getBy(enumClass, constant -> ObjUtil.equals(condition.callWithRuntimeException(constant), value));
    }

    public static <E extends Enum<E>, C> E getByValue(Class<E> enumClass, C value) {
        // 只有实现了 ICommonEnum 接口才可以使用
        if (!ClassUtil.isAssignable(ICommonEnum.class, enumClass)) {
            return null;
        }
        return getBy(enumClass, constant -> ((ICommonEnum<?>) constant).getValue().equals(value));
    }

}
