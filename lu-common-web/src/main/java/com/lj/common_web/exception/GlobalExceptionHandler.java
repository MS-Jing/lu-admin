package com.lj.common_web.exception;

import com.lj.common.exception.CommonException;
import com.lj.common_web.response.ResponseCode;
import com.lj.common_web.response.ResponseVO;
import com.lj.common_web.utils.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author luojing
 * @version 1.0
 * @date 2023/3/16 9:59
 * 全局异常处理
 * 返回统一响应体
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 公共异常
     */
    @ExceptionHandler(CommonException.class)
    public ResponseVO<String> handler(CommonException e) {
        reportLog(e);
        ResponseCode resultCode = ResponseCode.getResponseCode(e.getCode());
        if (resultCode != null) {
            return ResponseVO.error(resultCode, e.getMsg());
        }
        return ResponseVO.error(ResponseCode.FAILED, e.getMsg());
    }

    /**
     * 方法不被支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseVO<String> handler(HttpRequestMethodNotSupportedException e) {
        reportLog(e);
        return ResponseVO.of(ResponseCode.METHOD_NOT_ALLOWED);
    }

    /**
     * 实体校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseVO<String> handler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        reportLog(e, objectError.getDefaultMessage());
        return ResponseVO.error(ResponseCode.BAD_REQUEST, objectError.getDefaultMessage());
    }

    /**
     * 断言异常
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseVO<String> handler(IllegalArgumentException e) {
        reportLog(e);
        return ResponseVO.error(ResponseCode.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseVO<String> handler(Exception e) {
        reportLog(e);
        return ResponseVO.of(ResponseCode.FAILED);
    }

    private void reportLog(Exception e) {
        reportLog(e, e.getMessage());
    }

    private void reportLog(Exception e, String exceptionMessage) {
        log.error("request url: [" + ServletUtil.getRequest().getRequestURI() + "]; " + e.getClass().getSimpleName() + ": [" + exceptionMessage + "]");
    }
}
