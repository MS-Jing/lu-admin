package com.lj.mp.model;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @author luojing
 * @version 1.0
 * @date 2023/3/20 10:16
 */
public class StandardServiceImpl<M extends StandardMapper<T>, T extends StandardEntity> extends ServiceImpl<M, T> implements StandardService<T> {

    @Override
    public int insertBatchSomeColumn(List<T> batchList, int batchSize) {
        if (CollUtil.isEmpty(batchList)) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < batchList.size(); i += batchSize) {
            int end = Math.min(batchList.size(), i + batchSize);
            List<T> subList = batchList.subList(i, end);
            count += baseMapper.insertBatchSomeColumn(subList);
        }
        return count;
    }
}
