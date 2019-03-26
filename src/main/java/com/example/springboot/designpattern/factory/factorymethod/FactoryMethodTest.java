package com.example.springboot.designpattern.factory.factorymethod;

import com.example.springboot.designpattern.factory.Product;

/**
 * Description:工厂方法模式测试类
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        Factory factoryA = new FactoryA();
        Product productA = factoryA.create();
        productA.show();
    }
}
