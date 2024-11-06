package com.lj.mp.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lj.common.enums.SortordEnum;
import com.lj.common.exception.CommonException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author luojing
 * @since 2024/11/6 18:05
 * 依赖mp的分页查询工具
 */
@Slf4j
public class PageQueryUtils {

    /**
     * 从分页查询参数中 获取page对象
     *
     * @param pageQueryParams 分页查询参数
     * @param <T>             查询对应的实体类型
     * @return page对象
     */
    public static <T> Page<T> getPage(AbstractPageQueryParams pageQueryParams) {
        Page<T> page = new Page<>();
        // 当前需要查询的页数
        if (pageQueryParams.getCurrent() != null) {
            page.setCurrent(pageQueryParams.getCurrent());
        }
        // 当前查询的大小
        if (pageQueryParams.getSize() != null) {
            page.setSize(pageQueryParams.getSize());
        }
        // 排序
        page.addOrder(getOrder(pageQueryParams));
        return page;
    }

    /**
     * 从分页查询参数中 获取排序列表
     *
     * @param pageQueryParams 分页查询参数
     * @return 排序列表
     */
    public static List<OrderItem> getOrder(AbstractPageQueryParams pageQueryParams) {
        List<String> sortList = pageQueryParams.getSortList();
        if (CollUtil.isEmpty(sortList)) {
            return Collections.emptyList();
        }
        List<OrderItem> result = new ArrayList<>();
        for (String sort : sortList) {
            if (StrUtil.isBlank(sort)) {
                continue;
            }
            try {
                result.add(resolveSortStr(sort));
            } catch (Exception e) {
                log.warn("解析排序字段: ", e);
            }
        }
        return result;
    }

    private static OrderItem resolveSortStr(String sortStr) {
        String[] split = sortStr.trim().split(StrPool.COLON);
        if (split.length != 2) {
            throw new CommonException("排序字段格式错误:" + sortStr);
        }
        boolean isAsc = SortordEnum.ASC.getValue().equalsIgnoreCase(split[1].trim());
        String column = split[0].trim();
        return isAsc ? OrderItem.asc(StrUtil.toUnderlineCase(column)) : OrderItem.desc(StrUtil.toUnderlineCase(column));
    }
}
