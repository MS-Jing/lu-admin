package com.lj.core.model;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author luojing
 * @version 1.0
 * @date 2023/3/20 10:03
 * <p>
 * 规范 Mapper，可以添加自定义公共方法，注意也需要再规范的service中添加哦！
 */
public interface StandardMapper<T> extends BaseMapper<T> {
}
