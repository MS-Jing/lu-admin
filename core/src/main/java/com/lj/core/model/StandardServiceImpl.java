package com.lj.core.model;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @author luojing
 * @version 1.0
 * @date 2023/3/20 10:16
 */
public class StandardServiceImpl<M extends StandardMapper<T>, T> extends ServiceImpl<M, T> implements StandardService<T> {

    @Override
    public StandardMapper<T> getStandardMapper() {
        return baseMapper;
    }
}
