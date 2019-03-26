package com.example.springboot.designpattern.factory.abstractfactory;

import com.example.springboot.designpattern.factory.Product;

/**
 * Description:抽象工厂模式测试类
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        AbstractFactory abstractFactoryA = new AbstractFactoryA();
        Product product = abstractFactoryA.createProduct();
        Gift gift = abstractFactoryA.createGift();

        product.show();
        gift.showGift();
    }
}
