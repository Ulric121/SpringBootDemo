package com.example.springboot.designpattern.proxy.staticproxy;

import com.example.springboot.designpattern.proxy.RealStar;
import com.example.springboot.designpattern.proxy.Star;

/**
 * Description:静态代理测试类
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class StaticProxyTest {

    public static void main(String[] args) {
        Star realStar = new RealStar();
        Star proxyStar = new ProxyStar(realStar);
        proxyStar.sing();
    }
}
