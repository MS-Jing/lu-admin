package com.lj.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author luojing
 * @since 2024/7/25 17:17
 */
@Data
public class CommonException extends RuntimeException {
    private Integer code;

    private String msg;

    public CommonException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CommonException(String msg) {
        this(500, msg);
    }
}
