package com.lj.utils.query;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Data;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
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
    private List<ParamsFieldDetail> fieldDetailList = new ArrayList<>();

    /**
     * 区间查询
     */
    private List<BetweenDetails> betweenDetailsList = new ArrayList<>();

    public ParamsDetails(Class<? extends AbstractQueryParams> paramsClass) {
        this.paramsClass = paramsClass;
    }

    public void addCanOrder(OrderItem orderItem) {
        this.canOrderList.add(orderItem);
    }

    public void addCanOrder(List<OrderItem> orderItems) {
        this.canOrderList.addAll(orderItems);
    }


    public void addFieldDetail(ParamsFieldDetail<? extends Annotation> paramsFieldDetail) {
        this.fieldDetailList.add(paramsFieldDetail);
    }

    public void addBetweenDetails(BetweenDetails betweenDetails) {
        addBetweenDetails(Collections.singletonList(betweenDetails));
    }

    public void addBetweenDetails(List<BetweenDetails> betweenDetailsList) {
        this.betweenDetailsList.addAll(betweenDetailsList);
    }
}
