package com.lj.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.lj.sys.standard.SysStandardEntity;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author lj
 * @since 2024-12-10 17:21:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class SysRole extends SysStandardEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;
}