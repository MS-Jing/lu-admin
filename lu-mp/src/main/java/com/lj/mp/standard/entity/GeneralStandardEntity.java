package com.lj.mp.standard.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author luojing
 * @since 2024/7/26 16:41
 * 通用的标准实体，拥有所有的基础字段
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class GeneralStandardEntity extends IdLongStandardEntity {

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Version
    @TableField(value = "version", fill = FieldFill.INSERT)
    private Integer version;

    @TableLogic(value = "0", delval = "1")
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    private Boolean deleted;
}
