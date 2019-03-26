package com.example.springboot.designpattern.singleton;

/**
 * Description:单例模式经典实现方式懒汉模式，线程不安全
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class Singleton1 {

    /**
     * 静态变量
     */
    private static Singleton1 singleton1;

    /**
     * 私有构造器
     */
    private Singleton1() {
    }

    /**
     * 静态方法
     *
     * @return singleton1
     */
    public static Singleton1 getInstance() {
        if (singleton1 == null) {
            singleton1 = new Singleton1();
        }
        return singleton1;
    }
}
