package com.example.springboot.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Description:Stream处理集合
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/21
 */
public class StreamDemo {

    public static void main(String[] args) {

        /**
         * 常用的中间操作：filter，map，limit，sorted，distinct
         */
        System.out.println("filter 方法用于通过设置的条件过滤出元素，以下代码片段使用 filter 方法过滤掉空字符串");
        List<String> strFilter = Arrays.asList("Hollis", "", "HollisChuang", "H", "hollis");
        strFilter.stream().filter(string -> !string.isEmpty()).forEach(System.out::println);

        System.out.println("map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数");
        List<Integer> numberMap = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numberMap.stream().map(i -> i * i).forEach(System.out::println);

        System.out.println("limit 返回 Stream 的前面 n 个元素，以下代码片段使用 limit 方法保理4个元素");
        List<Integer> numberLimit = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numberLimit.stream().limit(4).forEach(System.out::println);

        System.out.println("sorted 方法用于对流进行排序，以下代码片段使用 sorted 方法进行排序");
        List<Integer> numberSorted = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numberSorted.stream().sorted().forEach(System.out::println);

        System.out.println("distinct 主要用来去重，以下代码片段使用 distinct 对元素进行去重");
        List<Integer> numberDistinct = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numberDistinct.stream().distinct().forEach(System.out::println);

        System.out.println("一系列处理");
        List<String> strAll = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hello", "HelloWorld", "Hollis");
        strAll.stream().filter(string -> string.length() <= 6).map(String::length).sorted().limit(3)
                .distinct().forEach(System.out::println);

        /**
         * 常用的最终操作：forEach，count，collect
         */
        System.out.println("forEach 来迭代流中的每个数据，以下代码片段使用 forEach 输出了10个随机数");
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

        System.out.println("count 用来统计流中的元素个数");
        List<String> strCount = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hollis666", "Hello", "HelloWorld", "Hollis");
        System.out.println(strCount.stream().count());
    }
}
