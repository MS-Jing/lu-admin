package com.lj.sys.vo;

import com.lj.sys.enums.SysUserStatus;
import lombok.Data;

/**
 * @author luojing
 * @since 2024/11/7 18:27
 */
@Data
public class SysUserVo {

    private Long id;

    private String nickName;

    private String userName;

    private String password;

    private String email;

    private String mobile;

    private SysUserStatus userStatus;
}
