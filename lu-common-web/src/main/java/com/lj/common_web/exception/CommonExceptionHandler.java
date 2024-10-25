package com.lj.common_web.exception;

import com.lj.common.exception.CommonException;
import com.lj.common_web.response.ResponseCode;
import com.lj.common_web.response.ResponseVO;
import org.springframework.stereotype.Component;

/**
 * @author luojing
 * @since 2024/10/25 14:47
 * 公共异常处理器
 */
@Component
public class CommonExceptionHandler implements ExceptionHandler<CommonException> {
    @Override
    public Class<CommonException> getException() {
        return CommonException.class;
    }

    @Override
    public ResponseVO<String> handler(CommonException e) {
        ResponseCode resultCode = ResponseCode.getResponseCode(e.getCode());
        if (resultCode != null) {
            return ResponseVO.error(resultCode, e.getMsg());
        }
        return ResponseVO.error(ResponseCode.FAILED, e.getMsg());
    }
}
