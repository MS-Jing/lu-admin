package com.lj.common_web.exception;

import com.lj.common_web.response.ResponseCode;
import com.lj.common_web.response.ResponseVO;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;

/**
 * @author luojing
 * @since 2024/10/25 14:55
 * 接口请求方式不被支持异常处理
 */
@Component
public class HttpRequestMethodNotSupportedExceptionHandler implements ExceptionHandler<HttpRequestMethodNotSupportedException> {
    @Override
    public Class<HttpRequestMethodNotSupportedException> getException() {
        return HttpRequestMethodNotSupportedException.class;
    }

    @Override
    public ResponseVO<String> handler(HttpRequestMethodNotSupportedException e) {
        return ResponseVO.of(ResponseCode.METHOD_NOT_ALLOWED);
    }
}
