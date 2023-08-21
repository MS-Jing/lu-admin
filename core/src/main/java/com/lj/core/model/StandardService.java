package com.lj.core.model;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lj.core.utils.QueryWrapper;

import java.util.List;
import java.util.Map;

/**
 * @author luojing
 * @version 1.0
 * @date 2023/3/20 10:10
 */
public interface StandardService<T> extends IService<T> {

    StandardMapper<T> getStandardMapper();

    default List<Map<String, Object>> queryList(QueryWrapper<T> queryWrapper) {
        return getStandardMapper().queryList(queryWrapper);
    }


}
