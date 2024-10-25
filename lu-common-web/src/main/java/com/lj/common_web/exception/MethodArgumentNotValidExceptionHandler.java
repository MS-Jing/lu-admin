package com.lj.common_web.exception;

import com.lj.common_web.response.ResponseCode;
import com.lj.common_web.response.ResponseVO;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * @author luojing
 * @since 2024/10/25 14:59
 */
@Component
public class MethodArgumentNotValidExceptionHandler implements ExceptionHandler<MethodArgumentNotValidException> {
    @Override
    public Class<MethodArgumentNotValidException> getException() {
        return MethodArgumentNotValidException.class;
    }

    @Override
    public ResponseVO<String> handler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return ResponseVO.error(ResponseCode.BAD_REQUEST, objectError.getDefaultMessage());
    }
}
