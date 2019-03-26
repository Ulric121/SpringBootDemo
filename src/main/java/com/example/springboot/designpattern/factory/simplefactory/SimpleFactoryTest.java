package com.example.springboot.designpattern.factory.simplefactory;

import com.example.springboot.designpattern.factory.Product;

/**
 * Description:简单工厂模式测试类
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        Product productA = SimpleFactory.create("A");
        productA.show();
        Product productB = SimpleFactory.create("B");
        productB.show();
        Product productC = SimpleFactory.create("C");
        productC.show();
    }

}
