package com.lj.generator.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import com.lj.common.exception.CommonException;
import com.lj.generator.entity.GenTableConfig;
import com.lj.generator.entity.vo.TableInfoResult;
import com.lj.generator.mapper.GenTableConfigMapper;
import com.lj.generator.service.GenTableConfigService;
import com.lj.mp.standard.StandardServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collection;

/**
 * <p>
 * 生成器表配置 服务实现类
 * </p>
 *
 * @author lj
 * @since 2024-11-12
 */
@Service
public class GenTableConfigServiceImpl extends StandardServiceImpl<GenTableConfigMapper, GenTableConfig> implements GenTableConfigService {

    @Resource
    private DataSource dataSource;

    @Override
    public TableInfoResult loadTableInfo(String tableName) {
        if (StrUtil.isBlank(tableName)) {
            throw new CommonException("请输入正确的表名");
        }
        Table tableMeta = MetaUtil.getTableMeta(dataSource, tableName);
        Collection<Column> columns = tableMeta.getColumns();
        if (CollUtil.isEmpty(columns)) {
            throw new CommonException("请输入正确的表名");
        }
        return null;
    }
}
