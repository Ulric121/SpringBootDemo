package com.example.springboot.designpattern.factory.simplefactory;

import com.example.springboot.designpattern.factory.Product;
import com.example.springboot.designpattern.factory.ProductA;
import com.example.springboot.designpattern.factory.ProductB;
import com.example.springboot.designpattern.factory.ProductC;

/**
 * Description:简单工厂模式工厂类
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class SimpleFactory {

    public static Product create(String type) {
        if ("A".equals(type)) {
            return new ProductA();
        } else if ("B".equals(type)) {
            return new ProductB();
        } else {
            return new ProductC();
        }
    }
}
