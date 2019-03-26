package com.example.springboot.designpattern.singleton;

/**
 * Description:饿汉模式，线程安全
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class Singleton2 {

    /**
     * 静态变量
     */
    private static Singleton2 singleton2 = new Singleton2();

    /**
     * 私有构造器
     */
    private Singleton2() {
    }

    /**
     * 静态方法
     *
     * @return singleton2
     */
    public static Singleton2 getInstance() {
        return singleton2;
    }
}
