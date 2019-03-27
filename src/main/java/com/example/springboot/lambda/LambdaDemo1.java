package com.example.springboot.lambda;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Description:Lambda表达式演示1
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/27
 */
public class LambdaDemo1 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread one...");
            }
        }).start();

        // 匿名内部类可以修改为如下的写法
        new Thread(() -> System.out.println("thread two...")).start();

        // 将代码逻辑当作参数进行传递
        System.out.println(getAge(a -> Integer.parseInt(a), "123"));

        // 遍历map的方式
        Map<String, String> map = new HashMap<>(16);
        map.put("111", "111");
        map.put("222", "222");
        map.put("333", "333");
        map.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
        });
    }

    public static Integer getAge(Function<String, Integer> function, String args) {
        return function.apply(args);
    }
}
