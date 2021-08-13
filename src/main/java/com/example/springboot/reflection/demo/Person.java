package com.example.springboot.reflection.demo;

/**
 * Description:
 *
 * @author yangtao
 * @version 0.0.1
 * @since 2021/8/13
 */
public class Person {

    private String name;
    private int age;

    public Person() {

    }

    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String show(String nation) {
        System.out.println(this.name + " is from " + nation);
        return nation;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
