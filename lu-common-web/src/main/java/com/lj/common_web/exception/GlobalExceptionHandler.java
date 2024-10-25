package com.lj.common_web.exception;

import cn.hutool.core.util.ClassUtil;
import com.lj.common_web.response.ResponseCode;
import com.lj.common_web.response.ResponseVO;
import com.lj.common_web.utils.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    private List<com.lj.common_web.exception.ExceptionHandler<? extends Exception>> exceptionHandlerList;


    @ExceptionHandler(Exception.class)
    public ResponseVO<String> handler(Exception e) {
        reportLog(e);
        for (com.lj.common_web.exception.ExceptionHandler<? extends Exception> handler : exceptionHandlerList) {
            Class<?> exception = handler.getException();
            if (exception.equals(ClassUtil.getClass(e))) {
                return handler.toHandler(e);
            }
        }
        return ResponseVO.of(ResponseCode.FAILED);
    }

    private void reportLog(Exception e) {
        reportLog(e, e.getMessage());
    }

    private void reportLog(Exception e, String exceptionMessage) {
        HttpServletRequest request = ServletUtil.getRequest();
        log.error("ip: [{}];request url: [{}]; {}: [{}]", ServletUtil.getIpAddr(request), request.getRequestURI(), e.getClass().getSimpleName(), exceptionMessage);
    }
}
