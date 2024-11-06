package com.lj.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lj.mp.standard.entity.IdLongStandardEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author luojing
 * @since 2024/8/12 18:18
 */
@Getter
@Setter
@TableName("sys_user_role")
public class SysUserRole extends IdLongStandardEntity {

    private Long userId;

    private Long roleId;
}
