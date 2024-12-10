package com.lj.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.lj.mp.standard.entity.IdLongStandardEntity;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author lj
 * @since 2024-12-10 17:08:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_role")
public class SysUserRole extends IdLongStandardEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;
}