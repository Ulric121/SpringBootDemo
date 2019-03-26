package com.example.springboot.designpattern.factory.abstractfactory;

import com.example.springboot.designpattern.factory.Product;

/**
 * Description:定义抽象工厂接口，抽象工厂生产Product类产品和Gift类产品
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public interface AbstractFactory {

    Product createProduct();

    Gift createGift();
}
