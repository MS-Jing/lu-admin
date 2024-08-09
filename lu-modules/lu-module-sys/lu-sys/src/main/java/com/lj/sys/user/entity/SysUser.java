package com.lj.sys.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lj.sys.standard.SysStandardEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author lj
 * @since 2024-08-09
 */
@Getter
@Setter
@TableName("sys_user")
public class SysUser extends SysStandardEntity {

    private static final long serialVersionUID = 1L;

    private String nickName;

    private String userName;

    private String password;

    private String email;

    private String mobile;

    private Integer userStatus;


}
