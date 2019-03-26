package com.example.springboot.designpattern.singleton;

/**
 * Description:懒汉模式双重检查加锁，线程安全
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class Singleton4 {

    /**
     * 静态变量
     */
    private volatile static Singleton4 singleton4;

    /**
     * 私有构造器
     */
    private Singleton4() {
    }

    /**
     * 静态方法
     *
     * @return singleton4
     */
    public static Singleton4 getInstance() {
        if (singleton4 == null) {
            synchronized (Singleton4.class) {
                if (singleton4 == null) {
                    singleton4 = new Singleton4();
                }
            }
        }
        return singleton4;
    }
}
