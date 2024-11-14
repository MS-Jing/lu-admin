package com.lj.generator.service.impl;

import cn.hutool.core.text.StrPool;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import com.lj.common.utils.CheckUtils;
import com.lj.generator.entity.GenTableConfig;
import com.lj.generator.entity.vo.ColumnInfoResult;
import com.lj.generator.entity.vo.TableInfoResult;
import com.lj.generator.mapper.GenTableConfigMapper;
import com.lj.generator.service.GenTableConfigService;
import com.lj.generator.utils.GenUtils;
import com.lj.generator.utils.TypeMapper;
import com.lj.mp.standard.StandardServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        CheckUtils.ifBlank(tableName, "请输入正确的表名");

        Table tableMeta = MetaUtil.getTableMeta(dataSource, tableName);
        Collection<Column> columns = tableMeta.getColumns();

        CheckUtils.ifEmpty(columns, "请输入正确的表名");
        // 转换内容
        List<ColumnInfoResult> columnInfoList = new ArrayList<>();
        for (Column column : columns) {
            ColumnInfoResult columnInfo = ColumnInfoResult.builder()
                    .columnPk(column.isPk())
                    .columnName(column.getName())
                    .columnTypeName(column.getTypeName())
                    .columnSize((int) column.getSize())
                    .columnDigit(column.getDigit())
                    .fieldName(GenUtils.columnNameToFieldName(column.getName()))
                    .fieldType(TypeMapper.getJavaType(column.getType(), (int) column.getSize()))
                    .comment(column.getComment())
                    .required(!column.isNullable())
                    .build();
            columnInfoList.add(columnInfo);
        }
        String[] underline = tableName.split(StrPool.UNDERLINE);
        return TableInfoResult.builder()
                .tableName(tableMeta.getTableName())
                .comment(tableMeta.getComment())
                .tablePrefix(underline.length > 2 ? underline[0] : "")
                .columnInfoList(columnInfoList)
                .build();
    }
}
