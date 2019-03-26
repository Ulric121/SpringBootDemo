package com.example.springboot.designpattern.proxy.jdkdynamicproxy;

import com.example.springboot.designpattern.proxy.RealStar;
import com.example.springboot.designpattern.proxy.Star;

import java.lang.reflect.Proxy;

/**
 * Description:jdk动态代理测试类
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class JdkDynamicProxyTest {

    public static void main(String[] args) {
        Star realStar = new RealStar();
        JdkDynamicProxyHandler handler = new JdkDynamicProxyHandler(realStar);

        // 创建代理类
        Star proxyStar = (Star) Proxy.newProxyInstance(
                realStar.getClass().getClassLoader(),
                realStar.getClass().getInterfaces(),
                handler);

        proxyStar.sing();
    }
}
