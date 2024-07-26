package com.lj.mp.model;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author luojing
 * @version 1.0
 * @date 2023/3/20 10:10
 */
public interface StandardService<T extends StandardEntity> extends IService<T> {

    default int insertBatchSomeColumn(@Param("list") List<T> batchList) {
        return insertBatchSomeColumn(batchList, DEFAULT_BATCH_SIZE);
    }

    int insertBatchSomeColumn(@Param("list") List<T> batchList, int batchSize);


}
