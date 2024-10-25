package com.lj.common_web.response;

import cn.hutool.core.util.TypeUtil;
import cn.hutool.json.JSONUtil;
import com.lj.common_web.annotation.ResponseResultVo;
import com.lj.common_web.constant.CommonConstant;
import com.lj.common_web.utils.ServletUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;


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
        // 判断方法上是否有ResponseResultVo注解
        ResponseResultVo methodAnnotation = returnType.getMethodAnnotation(ResponseResultVo.class);
        if (methodAnnotation != null) {
            return methodAnnotation.value();
        }
        //判断类上是否有ResponseResultVo注解
        ResponseResultVo annotation = returnType.getDeclaringClass().getAnnotation(ResponseResultVo.class);
        if (annotation != null) {
            return annotation.value();
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse response) {
        // 如果本身就是ResponseVO那么直接返回
        if (TypeUtil.getClass(returnType.getGenericParameterType()).equals(ResponseVO.class)) {
            return data;
        }
        // 将原本的数据包装在ResultVO里
        ResponseVO<Object> responseVO = ResponseVO.success(data);
        // 在请求头中提示这是被包装了的响应
        HttpServletResponse httpServletResponse = ServletUtil.getResponse();
        httpServletResponse.setHeader(CommonConstant.RESPONSE_WRAP_HEADER, CommonConstant.RESPONSE_WRAP_HEADER);
        // String类型不能直接返回，要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            // 将数据包装在ResultVO里后，再转换为json字符串响应给前端
            return JSONUtil.toJsonStr(responseVO);
        }
        return responseVO;
    }
}