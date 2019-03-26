package com.example.springboot.designpattern.factory.factorymethod;

import com.example.springboot.designpattern.factory.Product;

/**
 * Description:工厂方法模式工厂接口，可以实现“开闭原则”
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public interface Factory {

    Product create();
}
