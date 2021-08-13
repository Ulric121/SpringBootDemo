package com.example.springboot.reflection.demo;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Description:
 *
 * @author yangtao
 * @version 0.0.1
 * @since 2021/8/13
 */
public class Demo {

    @Test
    public void test() throws Exception {
        Class clazz = Person.class;

        // 调用私有构造器
        Constructor constructor = clazz.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        Person person = (Person) constructor.newInstance("Tom", 26);
        System.out.println(person);

        // 调用私有属性
        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);
        field.set(person, "William");
        System.out.println(person);

        // 调用私有方法
        Method method = clazz.getDeclaredMethod("show", String.class);
        method.setAccessible(true);
        String nation = (String) method.invoke(person, "USA");
        System.out.println(nation);
    }
}
