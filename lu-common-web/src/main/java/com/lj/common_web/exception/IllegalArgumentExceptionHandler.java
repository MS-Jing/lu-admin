package com.lj.common_web.exception;

import com.lj.common_web.response.ResponseCode;
import com.lj.common_web.response.ResponseVO;
import org.springframework.stereotype.Component;

/**
 * @author luojing
 * @since 2024/10/25 15:01
 * 断言异常处理
 */
@Component
public class IllegalArgumentExceptionHandler implements ExceptionHandler<IllegalArgumentException> {
    @Override
    public Class<IllegalArgumentException> getException() {
        return IllegalArgumentException.class;
    }

    @Override
    public ResponseVO<String> handler(IllegalArgumentException e) {
        return ResponseVO.error(ResponseCode.BAD_REQUEST, e.getMessage());
    }
}
