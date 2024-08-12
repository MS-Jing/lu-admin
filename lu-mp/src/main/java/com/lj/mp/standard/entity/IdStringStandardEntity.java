package com.lj.mp.standard.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lj.mp.standard.StandardEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author luojing
 * @since 2024/8/12 18:13
 * 主键id为String的实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class IdStringStandardEntity extends StandardEntity {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
}
