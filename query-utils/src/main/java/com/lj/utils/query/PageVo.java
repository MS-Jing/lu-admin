package com.lj.utils.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * @author luojing
 * @date 2023/3/11
 * 返回前端的分页vo
 */
@Getter
@ToString
public class PageVo<T> {

    /**
     * 数据记录列表
     */
    private List<T> records;

    /**
     * 总数
     */
    private Long total;

    /**
     * 每页大小
     */
    private Long size;

    /**
     * 当前页数
     */
    private Long current;

    /**
     * 总页数
     */
    private Long pages;

    public PageVo(IPage<T> page) {
        this.records = page.getRecords();
        this.total = page.getTotal();
        this.size = page.getSize();
        this.current = page.getCurrent();
        this.pages = page.getPages();
    }

    public PageVo(IPage<?> page, List<T> records) {
        this.total = page.getTotal();
        this.size = page.getSize();
        this.current = page.getCurrent();
        this.pages = page.getPages();
        this.records = records;
    }

}
