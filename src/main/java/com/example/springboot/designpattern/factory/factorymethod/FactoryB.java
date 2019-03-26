package com.example.springboot.designpattern.factory.factorymethod;

import com.example.springboot.designpattern.factory.Product;
import com.example.springboot.designpattern.factory.ProductB;

/**
 * Description:生产ProductB的工厂类
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class FactoryB implements Factory{

    @Override
    public Product create() {
        return new ProductB();
    }
}
