package com.lj.sys.dto;

import lombok.Data;

/**
 * @author luojing
 * @since 2024/10/29 13:58
 * 登录参数
 */
@Data
public class LoginDto {
    private String uuid;

    private String captcha;

    private String userName;

    private String password;
}
