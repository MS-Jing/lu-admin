package com.lj.common_web.response;

import cn.hutool.json.JSONUtil;
import com.lj.common_web.annotation.ResponseResultVo;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * 全局Controller拦截处理
 * 它会拦截全局所有的接口，统一按ResponseVO返回格式：
 * 你只需要在响应的Controller类或者接口上添加 @ResponseResultVo 注解
 * <p>
 * 所以开发人员可以只需要关注自己的业务，无需关心返回给前端的格式
 */
@RestControllerAdvice
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        //判断类上是否有ResponseResultVo注解
        ResponseResultVo annotation = returnType.getDeclaringClass().getAnnotation(ResponseResultVo.class);
        ResponseResultVo methodAnnotation = returnType.getMethodAnnotation(ResponseResultVo.class);
        return annotation != null || methodAnnotation != null;
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse response) {
        ResponseVO<Object> responseVO = ResponseVO.success(data);
        // String类型不能直接返回，要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            // 将数据包装在ResultVO里后，再转换为json字符串响应给前端
            return JSONUtil.toJsonStr(responseVO);
        }
        // 将原本的数据包装在ResultVO里
        return responseVO;
    }
}