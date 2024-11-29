package com.lj.common_web.utils;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
     *
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
            if (ObjectUtil.isNotEmpty(cookies)) {
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
     * 获取IP地址
     * <p>
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception ignored) {
        }

        return ip;
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

    /**
     * 设置png响应头
     */
    public static void setPngResponseHeader() {
        HttpServletResponse response = getResponse();
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
    }


    public static void setFileResponseHeader(String fileName) {
        setFileResponseHeader(fileName, -1);
    }

    /**
     * 设置文件流的响应头
     *
     * @param fileName 文件名称，带后缀名哦！
     */
    public static void setFileResponseHeader(String fileName, int size) {
        HttpServletResponse response = getResponse();
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        // 响应，告诉前端，这是一个附件，需要进行下载 文件名:filename
        response.setHeader("Content-Disposition", "attachment;filename=" + URLUtil.encode(fileName));
        if (size > 0) {
            response.addHeader("Content-Length", NumberUtil.toStr(size));
        }
    }
}
