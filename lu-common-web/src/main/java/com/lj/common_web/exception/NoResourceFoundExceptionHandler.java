package com.lj.common_web.exception;

import com.lj.common_web.response.ResponseCode;
import com.lj.common_web.response.ResponseVO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * @author luojing
 * @date 2024/11/24
 */
@Component
public class NoResourceFoundExceptionHandler implements ExceptionHandler<NoResourceFoundException> {
    @Override
    public Class<NoResourceFoundException> getException() {
        return NoResourceFoundException.class;
    }

    @Override
    public ResponseVO<String> handler(NoResourceFoundException e) {
        return ResponseVO.error(ResponseCode.NOT_FOUND, e.getMessage());
    }
}
