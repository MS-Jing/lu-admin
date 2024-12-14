package com.lj.generator.enums;

import com.lj.mp.standard.IStandardEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author luojing
 * @date 2024/11/28
 * mp的查询类型，主要用于生成查询接口中的查询条件
 */
@Getter
@AllArgsConstructor
public enum QueryType implements IStandardEnum<Integer> {

    /**
     * 等于 =，例如：WHERE age = 18
     */
    EQ(1, "=", "eq"),

    /**
     * 不等于 !=，例如：WHERE age != 18
     */
    NE(2, "!=", "ne"),

    /**
     * 大于 >，例如：WHERE age > 18
     */
    GT(3, ">", "gt"),

    /**
     * 大于等于 >= ，例如：WHERE age >= 18
     */
    GE(4, ">=", "ge"),

    /**
     * 小于 <，例如：WHERE age < 18
     */
    LT(5, "<", "lt"),

    /**
     * 小于等于 <=，例如：WHERE age <= 18
     */
    LE(6, "<=", "le"),

    /**
     * 范围查询，例如：WHERE age BETWEEN 10 AND 18
     * 暂时不支持
     */
//    BETWEEN(7, "BETWEEN"),

    /**
     * LIKE '%值%'，例如：WHERE nickname LIKE '%s%'
     */
    LIKE(8, "LIKE '%s%'", "like"),

    /**
     * LIKE '%值'，例如：WHERE nickname LIKE '%s'
     */
    LIKE_LEFT(9, "LIKE '%s'", "likeLeft"),

    /**
     * LIKE '值%'，例如：WHERE nickname LIKE 's%'
     */
    LIKE_RIGHT(10, "LIKE 's%'", "likeRight"),

    /**
     * 包含查询，例如：WHERE age IN (10, 20, 30)
     */
    IN(11, "IN", "in"),

    /**
     * 不包含查询，例如：WHERE age NOT IN (20, 30)
     */
    NOT_IN(12, "NOT IN", "notIn"),

    /**
     * 空查询，例如：WHERE email IS NULL
     * 暂不支持
     */
//    IS_NULL(13, "IS NULL", "isNull"),

    /**
     * 非空查询，例如：WHERE email IS NOT NULL
     * 暂不支持
     */
//    IS_NOT_NULL(14, "IS NOT NULL", "isNotNull"),
    ;

    private final Integer value;
    private final String desc;

    /**
     * mybatis-plus的QueryWrapper方法用于生成查询条件
     */
    private final String mpType;
}
