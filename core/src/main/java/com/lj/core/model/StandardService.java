package com.lj.core.model;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author luojing
 * @version 1.0
 * @date 2023/3/20 10:10
 */
public interface StandardService<T> extends IService<T> {

    StandardMapper<T> getStandardMapper();

//    List<Object>


}
