package com.lj.mp.standard;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author luojing
 * @version 1.0
 * @date 2023/3/20 10:10
 */
public interface StandardService<T extends StandardEntity> extends IService<T> {

    default LambdaQueryWrapper<T> lambdaQueryWrapper() {
        return Wrappers.lambdaQuery(getEntityClass());
    }

    default QueryWrapper<T> queryWrapper() {
        return Wrappers.query(getEntityClass());
    }

    default LambdaUpdateWrapper<T> lambdaUpdateWrapper() {
        return Wrappers.lambdaUpdate(getEntityClass());
    }

    default UpdateWrapper<T> updateWrapper() {
        return Wrappers.update();
    }

    default int insertBatchSomeColumn(@Param("list") List<T> batchList) {
        return insertBatchSomeColumn(batchList, DEFAULT_BATCH_SIZE);
    }

    int insertBatchSomeColumn(@Param("list") List<T> batchList, int batchSize);


}
