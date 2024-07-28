package com.lj.mp.standard;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.lj.common.enums.ICommonEnum;

import java.io.Serializable;

/**
 * @author luojing
 * @date 2024/7/28
 * 标准枚举 所有与数据库对应的枚举字段都应该实现该接口
 */
public interface IStandardEnum<T extends Serializable> extends IEnum<T>, ICommonEnum<T> {
}
