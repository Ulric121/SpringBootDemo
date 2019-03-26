package com.example.springboot.designpattern.factory.factorymethod;

import com.example.springboot.designpattern.factory.Product;
import com.example.springboot.designpattern.factory.ProductA;

/**
 * Description:生产ProductA的工厂类
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class FactoryA implements Factory {

    @Override
    public Product create() {
        return new ProductA();
    }
}
