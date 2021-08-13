package com.example.springboot.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/4/16
 */
public class Data {
    private static List<PersonModel> list = null;

    static {
        PersonModel wu = new PersonModel("wu qi", 18, "男");
        PersonModel zhang = new PersonModel("zhang san", 19, "男");
        PersonModel wang = new PersonModel("wang si", 20, "女");
        PersonModel zhao = new PersonModel("zhao wu", 20, "男");
        PersonModel chen = new PersonModel("chen liu", 21, "男");
        list = new ArrayList<>(Arrays.asList(wu, zhang, wang, zhao, chen));
    }

    /**
     * 过滤所有男性
     */
    public static void filterSex() {
        list.stream().filter(personModel -> "男".equals(personModel.getGender()))
                .forEach(System.out::println);
    }

    /**
     * 过滤所有男性并且年龄小于20岁
     */
    public static void filterSexAndAge() {
        list.stream().filter(personModel -> "男".equals(personModel.getGender()) && personModel.getAge() < 20)
                .forEach(System.out::println);
    }

    /**
     * 取出所有用户的名字
     * map生成一对一的映射
     */
    public static void getUserNameList() {
        list.stream().map(PersonModel::getName).forEach(System.out::println);
    }

    /**
     * flatMap是一对多的映射
     */
    public static void flatMapString() {
        list.stream().flatMap(personModel -> Arrays.stream(personModel.getName().split(" ")))
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        filterSex();
        System.out.println("---");
        filterSexAndAge();
        System.out.println("---");
        getUserNameList();
        System.out.println("---");
        flatMapString();
    }
}
