package com.lj.utils.query;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lj.utils.query.annotation.CanOrder;
import com.lj.utils.query.annotation.OrderBy;
import com.lj.utils.query.condition.AbstractConditionHandler;
import com.lj.utils.query.condition.ConditionManager;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author luojing
 * @date 2023/8/10
 * 列表查询工具类
 */
public class QueryUtils {

    private static final Map<Class<? extends AbstractQueryParams>, ParamsDetails> PARAMS_DETAILS_MAP = new ConcurrentHashMap<>();

    public static <T> List<T> query(IService<T> iService, AbstractQueryParams queryParams) {
        return query(iService, queryParams, Function.identity());
    }

    public static <T, R> List<R> query(IService<T> iService, AbstractQueryParams queryParams, Function<T, R> convert) {
        return query(iService, queryParams, getQueryWrapper(queryParams), convert);
    }

    /**
     * 获取QueryWrapper对象
     * 如果查询参数字段有相关的条件注解会直接添加进QueryWrapper对象中
     * @param queryParams 查询参数
     * @param <T> 实体类型
     * @return QueryWrapper对象
     */
    public static <T> QueryWrapper<T> getQueryWrapper(AbstractQueryParams queryParams) {
        Class<? extends AbstractQueryParams> paramsClass = queryParams.getClass();
        ParamsDetails paramsDetails = getParamsDetails(paramsClass);
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        for (ParamsFieldDetail fieldDetails : paramsDetails.getFieldDetailList()) {
            fieldDetails.getHandler().handleCondition(wrapper, fieldDetails, queryParams);
        }
        return wrapper;
    }

    /**
     * 根据查询条件参数获取查询后的数据
     *
     * @param iService    业务实现类
     * @param queryParams 查询条件参数
     * @param wrapper     mybatis-plus的查询条件包装器
     * @param convert     转换器，可以将查询出的数据库对应实体转换成目标Vo
     * @param <T>         数据库对应的实体类型
     * @param <R>         目标Vo实体类型
     * @return 分页查询后的数据
     */
    public static <T, R> PageVo<R> queryPage(IService<T> iService, AbstractQueryParams queryParams, QueryWrapper<T> wrapper, Function<T, R> convert) {
        Page<T> page = getPage(queryParams);
        if (wrapper != null) {
            iService.page(page, wrapper);
        } else {
            iService.page(page);
        }
        // 类型转换 实体转换成vo
        List<T> records = page.getRecords();
        return new PageVo<>(page, records.stream().map(convert).collect(Collectors.toList()));
    }

    /**
     * 根据查询条件参数获取查询后的数据
     *
     * @param iService    业务实现类
     * @param queryParams 查询条件参数
     * @param wrapper     mybatis-plus的查询条件包装器
     * @param convert     转换器，可以将查询出的数据库对应实体转换成目标Vo
     * @param <T>         数据库对应的实体类型
     * @param <R>         目标Vo实体类型
     * @return 查询后的数据
     */
    public static <T, R> List<R> query(IService<T> iService, AbstractQueryParams queryParams, QueryWrapper<T> wrapper, Function<T, R> convert) {
        List<OrderItem> orderItemList = getOrder(queryParams);
        List<T> result;
        if (CollUtil.isNotEmpty(orderItemList)) {
            if (wrapper == null) {
                wrapper = new QueryWrapper<>();
            }
            for (OrderItem orderItem : orderItemList) {
                wrapper.orderBy(true, orderItem.isAsc(), orderItem.getColumn());
            }
        }
        if (wrapper != null) {
            result = iService.list(wrapper);
        } else {
            // 没有任何条件
            result = iService.list();
        }
        return result.stream().map(convert).collect(Collectors.toList());
    }

    /**
     * 获取page对象
     *
     * @param queryParams 查询参数
     * @param <T>         查询对应的实体类型
     * @return page对象
     */
    public static <T> Page<T> getPage(AbstractQueryParams queryParams) {
        Page<T> page = new Page<>();
        // 当前需要查询的页数
        if (queryParams.getCurrent() != null) {
            page.setCurrent(queryParams.getCurrent());
        }
        // 当前查询的大小
        if (queryParams.getSize() != null) {
            page.setSize(queryParams.getSize());
        }
        // 排序
        page.addOrder(getOrder(queryParams));
        return page;
    }

    public static List<OrderItem> getOrder(AbstractQueryParams queryParams) {
        // 所有可排序字段
        List<OrderItem> canParamsOrder = getParamsDetails(queryParams.getClass()).getCanOrderList();
        Map<String, OrderItem> canParamsOrderMap = canParamsOrder.stream().collect(Collectors.toMap(OrderItem::getColumn, Function.identity()));
        // 判断前端传递的排序字段是否可以进行排序
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.addAll(getOrderItems(true, queryParams.getAscOrders()));
        orderItemList.addAll(getOrderItems(false, queryParams.getDescOrders()));
        List<OrderItem> result = new ArrayList<>();
        for (OrderItem orderItem : orderItemList) {
            OrderItem item = canParamsOrderMap.get(orderItem.getColumn());
            if (item != null && orderItem.isAsc() == item.isAsc()) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * 获取请求参数类详情信息
     * 如果请求参数详情没有获取那么先创建
     *
     * @param paramsClass 请求参数class对象
     * @return 请求参数类详情信息
     */
    private static ParamsDetails getParamsDetails(Class<? extends AbstractQueryParams> paramsClass) {
        ParamsDetails paramsDetails = PARAMS_DETAILS_MAP.get(paramsClass);
        // 双重检测锁防止重复初始化查询参数信息
        if (paramsDetails == null) {
            synchronized (paramsClass) {
                if (PARAMS_DETAILS_MAP.get(paramsClass) == null) {
                    paramsDetails = new ParamsDetails(paramsClass);
                    // 初始化字段列表信息
                    Field[] paramFields = ReflectUtil.getFields(paramsClass);
                    for (Field paramField : paramFields) {
                        // 条件注解
                        Annotation[] annotations = paramField.getAnnotations();
                        for (Annotation annotation : annotations) {
                            AbstractConditionHandler handler = ConditionManager.getHandler(annotation.annotationType());
                            if (handler != null) {
                                paramsDetails.addFieldDetail(handler.handleParamsField(paramField, annotation));
                            }
                        }
                        // 排序字段
                        CanOrder canOrder = paramField.getAnnotation(CanOrder.class);
                        if (canOrder != null && canOrder.canOrder()) {
                            paramsDetails.addCanOrder(getOrderItem(canOrder.isAsc(), paramField.getName()));
                        }
                    }
                    // 类上的条件注解

                    // 获取类上的@OrderBy注解
                    OrderBy orderBy = paramsClass.getAnnotation(OrderBy.class);
                    if (orderBy != null) {
                        paramsDetails.addCanOrder(getOrderItems(true, orderBy.asc()));
                        paramsDetails.addCanOrder(getOrderItems(false, orderBy.desc()));
                    }

                    // 最后记得放回缓存中
                    PARAMS_DETAILS_MAP.put(paramsClass, paramsDetails);
                }
            }
        }
        return paramsDetails;
    }

    private static List<OrderItem> getOrderItems(boolean isAsc, String... columns) {
        if (ArrayUtil.isEmpty(columns)) {
            return Collections.emptyList();
        }
        List<OrderItem> result = new ArrayList<>();
        for (String column : columns) {
            result.add(getOrderItem(isAsc, column));
        }
        return result;
    }

    private static List<OrderItem> getOrderItems(boolean isAsc, List<String> columns) {
        if (CollUtil.isEmpty(columns)) {
            return Collections.emptyList();
        }
        List<OrderItem> result = new ArrayList<>();
        for (String column : columns) {
            result.add(getOrderItem(isAsc, column));
        }
        return result;
    }

    /**
     * 构造排序项
     * @param isAsc 是否是升序排序
     * @param column 数据库列
     * @return 排序项
     */
    private static OrderItem getOrderItem(boolean isAsc, String column) {
        return isAsc ? OrderItem.asc(StrUtil.toUnderlineCase(column)) : OrderItem.desc(StrUtil.toUnderlineCase(column));
    }
}
