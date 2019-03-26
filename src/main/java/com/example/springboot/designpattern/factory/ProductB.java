package com.example.springboot.designpattern.factory;

/**
 * Description:定义产品B
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class ProductB implements Product{

    @Override
    public void show() {
        System.out.println("this is ProductB");
    }
}
