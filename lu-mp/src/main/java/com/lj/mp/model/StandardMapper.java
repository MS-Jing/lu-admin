package com.lj.mp.model;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author luojing
 * @version 1.0
 * @date 2023/3/20 10:03
 * <p>
 * 规范 Mapper，可以添加自定义公共方法，注意也需要在{@link StandardService}中添加哦！
 */
public interface StandardMapper<T extends StandardEntity> extends BaseMapper<T> {

    /**
     * mp官方提供的批量插入插入的插件
     * 性能比{@link StandardService#saveBatch} 要好
     *
     * @param batchList 批量插入的列表
     * @return 影响的行数
     */
    int insertBatchSomeColumn(@Param("list") List<T> batchList);
}
