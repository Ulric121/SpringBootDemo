package com.example.springboot.designpattern.factory.abstractfactory;

/**
 * Description:定义GiftA
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class GiftA implements Gift {

    @Override
    public void showGift() {
        System.out.println("this is GiftA");
    }
}
