package com.lj.common_web.response;

import lombok.Data;

/**
 * @author luojing
 * @version 1.0
 * @date 2023/3/15 16:32
 * 统一结果返回
 */
@Data
public class ResponseVO<T> {
    /**
     * 状态码
     */
    private int code;
    /**
     * 响应信息，用来说明响应情况
     */
    private String msg;
    /**
     * 响应的具体数据
     */
    private T data;

    private ResponseVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ResponseVO(ResponseCode responseCode, T data) {
        this(responseCode.getCode(), responseCode.getMsg(), data);
    }

    public static <T> ResponseVO<T> success(T data) {
        return of(ResponseCode.SUCCESS, data);
    }

    public static <T> ResponseVO<T> error(ResponseCode responseCode, String msg) {
        ResponseVO<T> instance = of(responseCode);
        instance.setMsg(msg);
        return instance;
    }

    public static <T> ResponseVO<T> of(Integer code, String msg) {
        return of(code, msg, null);
    }

    public static <T> ResponseVO<T> of(Integer code, String msg, T data) {
        return new ResponseVO<>(code, msg, data);
    }

    public static <T> ResponseVO<T> of(ResponseCode responseCode) {
        return of(responseCode, null);
    }

    public static <T> ResponseVO<T> of(ResponseCode responseCode, T data) {
        return new ResponseVO<>(responseCode, data);
    }


}
