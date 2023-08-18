package com.lj.utils.query.details;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.lj.utils.query.AbstractQueryParams;
import lombok.Data;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luojing
 * @date 2023/8/14
 * 参数详情
 */
@Data
public class ParamsDetails {
    /**
     * 参数的class对象
     */
    private Class<? extends AbstractQueryParams> paramsClass;

    /**
     * 可排序列表
     */
    private List<OrderItem> canOrderList = new ArrayList<>();

    /**
     * 字段详情列表
     */
    private List<ConditionDetails> conditionDetailsList = new ArrayList<>();

    public ParamsDetails(Class<? extends AbstractQueryParams> paramsClass) {
        this.paramsClass = paramsClass;
    }

    public void addCanOrder(OrderItem orderItem) {
        this.canOrderList.add(orderItem);
    }

    public void addCanOrder(List<OrderItem> orderItems) {
        this.canOrderList.addAll(orderItems);
    }

    public void addConditionDetails(ConditionDetails<? extends Annotation> conditionDetails) {
        this.conditionDetailsList.add(conditionDetails);
    }

    public void addConditionDetails(List<ConditionDetails> conditionDetailsList) {
        this.conditionDetailsList.addAll(conditionDetailsList);
    }

}
