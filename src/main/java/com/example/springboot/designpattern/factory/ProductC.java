package com.example.springboot.designpattern.factory;

/**
 * Description:定义产品C
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class ProductC implements Product{

    @Override
    public void show() {
        System.out.println("this is ProductC");
    }
}
