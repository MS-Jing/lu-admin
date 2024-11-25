package com.lj.common_web.exception;

import com.lj.common_web.response.ResponseVO;

/**
 * @author luojing
 * @since 2024/10/25 14:43
 */
public interface ExceptionHandler<E extends Exception> {

    /**
     * @return 异常的类型
     */
    Class<E> getException();

    default E cast(Exception e) {
        return (E) e;
    }

    default ResponseVO<String> toHandler(Exception e) {
        return handler(cast(e));
    }

    /**
     * 异常的处理
     *
     * @param e 异常实例
     * @return 向前端响应
     */
    ResponseVO<String> handler(E e);

    /**
     * 打印日志，这个不会向前端展示，只是为了打印日志好排查问题
     *
     * @param e 异常实例
     * @return 出现异常时的异常信息
     */
    default String reportLog(Exception e) {
        return e.getMessage();
    }
}
