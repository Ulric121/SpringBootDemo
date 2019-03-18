package com.example.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/8
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(com.example.springboot.aop.Log)")
    public void annotationPointCut() {}

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint) {
        List<Object> list = new ArrayList<>(Arrays.asList(joinPoint.getArgs()));
        System.out.println(list);
    }
}
