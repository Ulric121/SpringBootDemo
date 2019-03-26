package com.example.springboot.designpattern.factory.abstractfactory;

import com.example.springboot.designpattern.factory.Product;
import com.example.springboot.designpattern.factory.ProductA;

/**
 * Description:抽象工厂A生产ProductA和GiftA
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class AbstractFactoryA implements AbstractFactory{
    @Override
    public Product createProduct() {
        return new ProductA();
    }

    @Override
    public Gift createGift() {
        return new GiftA();
    }
}
