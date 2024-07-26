package com.lj.common.exception;

/**
 * @author luojing
 * @since 2024/7/25 17:21
 * 不受支持的操作异常
 */
public class UnsupportedOperationException extends CommonException {

    public UnsupportedOperationException(){
        this("不被支持的操作！");
    }

    public UnsupportedOperationException(String msg) {
        super(msg);
    }
}
