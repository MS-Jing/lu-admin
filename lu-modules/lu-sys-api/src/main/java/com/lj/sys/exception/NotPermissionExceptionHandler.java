package com.lj.sys.exception;

import cn.dev33.satoken.exception.NotPermissionException;
import com.lj.common_web.exception.ExceptionHandler;
import com.lj.common_web.response.ResponseCode;
import com.lj.common_web.response.ResponseVO;
import org.springframework.stereotype.Component;

/**
 * @author luojing
 * @since 2024/11/8 17:09
 */
@Component
public class NotPermissionExceptionHandler implements ExceptionHandler<NotPermissionException> {
    @Override
    public Class<NotPermissionException> getException() {
        return NotPermissionException.class;
    }

    @Override
    public ResponseVO<String> handler(NotPermissionException e) {
        return ResponseVO.of(ResponseCode.FORBIDDEN);
    }
}
