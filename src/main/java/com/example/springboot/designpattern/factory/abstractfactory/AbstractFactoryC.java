package com.example.springboot.designpattern.factory.abstractfactory;

import com.example.springboot.designpattern.factory.Product;
import com.example.springboot.designpattern.factory.ProductC;

/**
 * Description:抽象工厂C生产ProductC和GiftA
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class AbstractFactoryC implements AbstractFactory {

    @Override
    public Product createProduct() {
        return new ProductC();
    }

    @Override
    public Gift createGift() {
        return new GiftA();
    }
}
