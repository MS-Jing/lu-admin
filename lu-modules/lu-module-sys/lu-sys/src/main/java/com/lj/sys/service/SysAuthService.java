package com.lj.sys.service;

import java.io.OutputStream;

/**
 * @author luojing
 * @since 2024/10/29 10:26
 */
public interface SysAuthService {
    void captcha(String uuid, OutputStream out);

}
