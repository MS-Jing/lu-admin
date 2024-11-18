package com.lj.auth.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.lj.auth.params.LoginParams;

import java.io.OutputStream;

/**
 * @author luojing
 * @since 2024/10/29 10:26
 */
public interface SysAuthService {
    void captcha(String uuid, OutputStream out);

    boolean validate(String uuid,String captcha);

    SaTokenInfo doLogin(LoginParams params);

    void logout();
}
