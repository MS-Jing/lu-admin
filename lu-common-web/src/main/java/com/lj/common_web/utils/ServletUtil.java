package com.lj.common_web.utils;

import cn.hutool.core.util.ObjectUtil;
import com.lj.common.exception.UnsupportedOperationException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author luojing
 * @version 1.0
 * @date 2023/3/22 9:22
 * <p>
 * HttpServlet工具类 可以获取当前请求的HttpServletRequest和HttpServletResponse对象
 */
public class ServletUtil {


    /**
     * 从请求体中获取参数
     * @param paramName 参数名称
     * @return 获取到的参数值
     */
    public static String getParamFromRequest(String paramName) {
        HttpServletRequest request = getRequest();

        // 1. 尝试从请求体里面读取
        String paramValue = request.getParameter(paramName);

        // 2. 尝试从header里读取
        if (ObjectUtil.isEmpty(paramValue)) {
            paramValue = request.getHeader(paramName);
        }
        // 3. 尝试从cookie里读取
        if (ObjectUtil.isEmpty(paramValue)) {
            Cookie[] cookies = request.getCookies();
            if(ObjectUtil.isNotEmpty(cookies)) {
                for (Cookie cookie : cookies) {
                    String cookieName = cookie.getName();
                    if (cookieName.equals(paramName)) {
                        return cookie.getValue();
                    }
                }
            }
        }
        // 4. 返回
        return paramValue;
    }

    /**
     * 获取当前请求的HttpServletRequest对象
     *
     * @return 当前请求的HttpServletRequest对象
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }

    /**
     * 获取当前请求的HttpServletResponse对象
     *
     * @return 当前请求的HttpServletResponse对象
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = getRequestAttributes();
        return servletRequestAttributes.getResponse();
    }

    /**
     * 获取当前请求的RequestAttributes对象
     *
     * @return 当前请求的RequestAttributes对象
     */
    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new UnsupportedOperationException();
        }
        return (ServletRequestAttributes) requestAttributes;
    }
}
