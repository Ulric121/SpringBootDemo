package com.example.springboot.designpattern.factory.abstractfactory;

import com.example.springboot.designpattern.factory.Product;
import com.example.springboot.designpattern.factory.ProductB;

/**
 * Description:抽象工厂B生产ProductB和GiftB
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class AbstractFactoryB implements AbstractFactory{

    @Override
    public Product createProduct() {
        return new ProductB();
    }

    @Override
    public Gift createGift() {
        return new GiftB();
    }
}
