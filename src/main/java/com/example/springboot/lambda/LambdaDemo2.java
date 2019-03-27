package com.example.springboot.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Description:Lambda表达式演示2
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/27
 */
public class LambdaDemo2 {
    private List<Apple> appleList = new ArrayList<>();

    /**
     * 获取红色苹果方法1
     *
     * @return 红色苹果
     */
    public List<Apple> getRedApple() {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : appleList) {
            if ("red".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 获取红色苹果方法2
     *
     * @param color 颜色
     * @return 红色苹果
     */
    public List<Apple> getAppleByColor(String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : appleList) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 获取红色苹果方法3
     *
     * @param predicate 断言
     * @return 红色苹果
     */
    public List<Apple> getApplyByPredicate(Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : appleList) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LambdaDemo2 appleStore = new LambdaDemo2();
        appleStore.getRedApple();
        appleStore.getAppleByColor("red");
        appleStore.getApplyByPredicate(apple -> apple.getColor().equals("red"));
    }
}

class Apple {
    private Integer id;

    private String color;

    private Integer weight;

    public Integer getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public Integer getWeight() {
        return weight;
    }
}