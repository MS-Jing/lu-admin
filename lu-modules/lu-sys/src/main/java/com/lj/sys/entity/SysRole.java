package com.lj.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lj.sys.standard.SysStandardEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author lj
 * @since 2024-08-12
 */
@Getter
@Setter
@TableName("sys_role")
public class SysRole extends SysStandardEntity {

    private static final long serialVersionUID = 1L;

    private String roleName;

    private String remark;


}
