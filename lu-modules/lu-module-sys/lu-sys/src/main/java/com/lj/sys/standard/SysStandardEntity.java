package com.lj.sys.standard;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lj.mp.standard.GeneralStandardEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author luojing
 * @since 2024/8/9 11:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class SysStandardEntity extends GeneralStandardEntity {

    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(value = "update_user", fill = FieldFill.INSERT)
    private Long updateUser;


}
