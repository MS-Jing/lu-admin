package com.lj.generator.service;

import com.lj.generator.entity.GenTableConfig;
import com.lj.generator.entity.vo.TableInfoResult;
import com.lj.mp.standard.StandardService;

import java.util.List;

/**
 * <p>
 * 生成器表配置 服务类
 * </p>
 *
 * @author lj
 * @since 2024-11-12
 */
public interface GenTableConfigService extends StandardService<GenTableConfig> {

    /**
     * @return 返回数据库没有配置的表名
     */
    List<String> enableGenTable();

    /**
     * 加载物理表信息
     *
     * @param tableName 物理表名
     * @return 物理表信息
     */
    TableInfoResult loadTableInfo(String tableName);

    List<String> optionalSuperClass();

}
