package com.example.springboot.threadlocal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description: 保存用户登录信息
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/1/14
 */
public class WebContextHolder {

    private static ThreadLocal<HttpServletRequest> httpServletRequestThreadLocal = new ThreadLocal<>();

    private static ThreadLocal<HttpServletResponse> httpServletResponseThreadLocal = new ThreadLocal<>();

    private static ThreadLocal<HttpSession> httpSessionThreadLocal = new ThreadLocal<>();

    public static HttpServletRequest getHttpRequest() {
        return httpServletRequestThreadLocal.get();
    }

    public static void setHttpRequest(HttpServletRequest request) {
        httpServletRequestThreadLocal.set(request);
    }

    public static HttpServletResponse getHttpResponse() {
        return httpServletResponseThreadLocal.get();
    }

    public static void setHttpResponse(HttpServletResponse response) {
        httpServletResponseThreadLocal.set(response);
    }

    public static HttpSession getSession() {
        return httpSessionThreadLocal.get();
    }

    public static void setSession(HttpSession session) {
        httpSessionThreadLocal.set(session);
    }

    public static void remove() {
        httpServletRequestThreadLocal.remove();
        httpServletResponseThreadLocal.remove();
        httpSessionThreadLocal.remove();
    }
}
