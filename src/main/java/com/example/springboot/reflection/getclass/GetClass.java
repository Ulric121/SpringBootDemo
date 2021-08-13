package com.example.springboot.reflection.getclass;

import com.example.springboot.reflection.demo.Person;
import org.junit.Test;

/**
 * Description:
 *
 * @author yangtao
 * @version 0.0.1
 * @since 2021/8/13
 */
public class GetClass {

    @Test
    public void test() throws Exception{

        // 方式一：调用运行时类的属性.class
        Class clazz1 = Person.class;
        System.out.println(clazz1);

        // 方式二：通过运行时类的对象，调用getClass()
        Person person = new Person();
        Class clazz2 = person.getClass();
        System.out.println(clazz2);

        // 方式三：调用Class的静态方法forName
        Class clazz3 = Class.forName("com.example.springboot.reflection.demo.Person");
        System.out.println(clazz3);

        // 方式四：使用类的加载器ClassLoader
        ClassLoader classLoader = GetClass.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("com.example.springboot.reflection.demo.Person");
        System.out.println(clazz4);

        System.out.println(clazz1 == clazz4);
    }
}
