package com.lj.sys.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.lj.common_web.exception.ExceptionHandler;
import com.lj.common_web.response.ResponseCode;
import com.lj.common_web.response.ResponseVO;
import org.springframework.stereotype.Component;

/**
 * @author luojing
 * @since 2024/10/25 15:29
 */
@Component
public class NotLoginExceptionHandler implements ExceptionHandler<NotLoginException> {
    @Override
    public Class<NotLoginException> getException() {
        return NotLoginException.class;
    }

    @Override
    public ResponseVO<String> handler(NotLoginException e) {
        return ResponseVO.of(ResponseCode.UNAUTHORIZED);
    }
}
