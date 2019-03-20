package com.example.springboot.threadlocal;

import com.example.springboot.redis.Person;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description: 自定义拦截器类
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/20
 */
public class DemoInterceptor extends HandlerInterceptorAdapter {

    /**
     * 在请求发生之前执行
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @return boolean
     * @throws Exception exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        // spring中获取session
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest().getSession();

        // 模拟从redis中查询客户信息并放入到ThreadLocal中
        Person person = new Person("1", "Tom", 27);
        session.setAttribute("user", person);
        WebContextHolder.setSession(session);

        return true;
    }

    /**
     * 在请求完成后执行
     *
     * @param request      request
     * @param response     response
     * @param handler      handler
     * @param modelAndView modelAndView
     * @throws Exception exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        long endTime = System.currentTimeMillis();
        System.out.println("本次处理时间：" + (endTime - startTime) + "ms");

        // 删除session中的客户信息
        WebContextHolder.remove();
        request.getSession().removeAttribute("user");
    }
}
