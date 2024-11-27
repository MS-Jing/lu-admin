package com.lj.generator.service;

import cn.hutool.core.collection.CollUtil;
import com.lj.generator.entity.GenColumnConfig;
import com.lj.mp.standard.StandardService;

import java.util.List;

/**
 * <p>
 * 生成器列配置 服务类
 * </p>
 *
 * @author lj
 * @since 2024-11-12
 */
public interface GenColumnConfigService extends StandardService<GenColumnConfig> {

    default List<GenColumnConfig> getByTableId(Long tableId) {
        return list(lambdaQueryWrapper()
                .eq(GenColumnConfig::getTableId, tableId)
                .orderByAsc(GenColumnConfig::getFieldSort));
    }

    default void deleteByTableId(List<Long> tableIds) {
        if (CollUtil.isEmpty(tableIds)) {
            return;
        }
        remove(lambdaQueryWrapper().in(GenColumnConfig::getTableId, tableIds));
    }
}
