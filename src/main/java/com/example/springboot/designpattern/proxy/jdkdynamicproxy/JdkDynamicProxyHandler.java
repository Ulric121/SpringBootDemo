package com.example.springboot.designpattern.proxy.jdkdynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Description:jdk动态代理处理类
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class JdkDynamicProxyHandler implements InvocationHandler {

    private static final String SING = "sing";

    private Object target;

    public JdkDynamicProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取当前执行方法的方法名
        String methodName = method.getName();
        Object result = null;
        if (SING.equals(methodName)) {
            System.out.println("明星代理先进行谈判...");
            result = method.invoke(target, args);
            System.out.println("明星代理去收钱...");
        }
        return result;
    }
}
