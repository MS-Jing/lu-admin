package com.lj.generator.service;

import com.lj.generator.entity.GenTableConfig;
import com.lj.generator.entity.vo.TableInfoResult;
import com.lj.mp.standard.StandardService;

/**
 * <p>
 * 生成器表配置 服务类
 * </p>
 *
 * @author lj
 * @since 2024-11-12
 */
public interface GenTableConfigService extends StandardService<GenTableConfig> {

    TableInfoResult loadTableInfo(String tableName);
}
