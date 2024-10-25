package com.lj.common_web.exception;

import com.lj.common_web.response.ResponseVO;

/**
 * @author luojing
 * @since 2024/10/25 14:43
 */
public interface ExceptionHandler<E extends Exception> {

    Class<E> getException();

    default ResponseVO<String> toHandler(Exception e) {
        return handler((E)e);
    }

    ResponseVO<String> handler(E e);
}
