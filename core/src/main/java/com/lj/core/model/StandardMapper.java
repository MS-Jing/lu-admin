package com.lj.core.model;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lj.core.utils.query.QueryWrapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author luojing
 * @version 1.0
 * @date 2023/3/20 10:03
 * <p>
 * 规范 Mapper，可以添加自定义公共方法，注意也需要再规范的service中添加哦！
 */
public interface StandardMapper<T> extends BaseMapper<T> {

    List<Map<String, Object>> queryList(@Param(Constants.WRAPPER) QueryWrapper<T> queryWrapper);
}
