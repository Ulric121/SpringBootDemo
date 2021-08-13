package com.example.springboot.reflection.classloader;

import org.junit.Test;

/**
 * Description:
 *
 * @author yangtao
 * @version 0.0.1
 * @since 2021/8/13
 */
public class ClassLoaderDemo {

    @Test
    public void test() {
        // 对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader1 = ClassLoaderDemo.class.getClassLoader();
        System.out.println(classLoader1);

        // 调用系统类加载器的getParent()，获取扩展类加载器
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);

        // 调用扩展类加载器的getParent()，无法获取引导类加载器
        ClassLoader classLoader3 = classLoader2.getParent();
        System.out.println(classLoader3);

        // 引导类加载器主要负责加载Java核心类库，比如String类
        ClassLoader classLoader4 = String.class.getClassLoader();
        System.out.println(classLoader4);
    }
}
