package com.lj.dict.service;

import com.lj.dict.params.DictQueryParams;
import com.lj.dict.result.EnumDictItem;
import com.lj.dict.result.EnumDictVo;

import java.util.List;

/**
 * @author luojing
 * @date 2024/11/15
 */
public interface EnumDictService {


    /**
     * 获取字典
     *
     * @param params 字典查询条件
     * @return 字典列表
     */
    List<EnumDictVo> getDict(DictQueryParams params);

    /**
     * 通过字典名称获取字典项
     *
     * @param dictName 字典名称
     * @return 字典项
     */
    List<EnumDictItem<Object>> getDictItemByName(String dictName);


}
