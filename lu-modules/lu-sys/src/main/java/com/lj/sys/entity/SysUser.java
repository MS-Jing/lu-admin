package com.lj.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.lj.sys.enums.SysUserStatus;
import com.lj.sys.standard.SysStandardEntity;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author luojing
 * @since 2024-12-10 14:49:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends SysStandardEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 移动电话
     */
    private String mobile;

    /**
     * 用户状态
     */
    private SysUserStatus userStatus;
}