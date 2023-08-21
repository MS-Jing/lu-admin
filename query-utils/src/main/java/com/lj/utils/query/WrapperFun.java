package com.lj.utils.query;

/**
 * @author luojing
 * @date 2023/8/11
 */
@FunctionalInterface
public interface WrapperFun {

    void condition(boolean condition, String column, Object val);
}
