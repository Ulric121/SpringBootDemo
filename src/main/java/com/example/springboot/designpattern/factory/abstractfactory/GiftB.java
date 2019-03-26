package com.example.springboot.designpattern.factory.abstractfactory;

/**
 * Description:定义GiftB
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class GiftB implements Gift {

    @Override
    public void showGift() {
        System.out.println("this is GiftB");
    }
}
