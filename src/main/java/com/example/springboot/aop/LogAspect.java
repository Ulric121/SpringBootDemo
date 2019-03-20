package com.example.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Description: 切面类
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/8
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(com.example.springboot.aop.Log)")
    public void annotationPointCut() {
    }

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint) {
        List<Object> list = new ArrayList<>(Arrays.asList(joinPoint.getArgs()));
        System.out.println(list);
    }

    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Exception {
        Object obj;

        // spring中获取request信息
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        // IP地址
        String ip = request.getRemoteAddr();
        // 用户代理
        String agent = request.getHeader("user-agent");
        // URI地址
        String uri = request.getRequestURI();
        // 请求方式
        String type = request.getMethod();

        // 得到方法签名
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        // 方法名称
        String methodName = method.getName();

        // 方法参数
        Object[] args = pjp.getArgs();

        // 注解信息
        Log log = method.getAnnotation(Log.class);
        // 模块名称
        String module = log.module();
        // 操作名称
        String operation = log.operation();

        // 32位主键编号
        String id = UUID.randomUUID().toString().replaceAll("-", "");

        try {
            // 执行方法
            obj = pjp.proceed();
        } catch (Throwable throwable) {
            System.out.println(getStackTraceAsString(throwable));
            throw new Exception(throwable);
        }

        return obj;
    }

    /**
     * 将ErrorStack转化为String
     *
     * @param throwable Throwable
     * @return 错误描述的字符串
     */
    private static String getStackTraceAsString(Throwable throwable) {
        if (throwable == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
