package com.example.springboot.designpattern.singleton;

/**
 * Description:懒汉模式方法加锁，线程安全
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class Singleton3 {

    /**
     * 静态变量
     */
    private static Singleton3 singleton3;

    /**
     * 私有构造方法
     */
    private Singleton3() {
    }

    /**
     * 静态方法
     *
     * @return singleton3
     */
    public static synchronized Singleton3 getInstance() {
        if (singleton3 == null) {
            singleton3 = new Singleton3();
        }
        return singleton3;
    }
}
