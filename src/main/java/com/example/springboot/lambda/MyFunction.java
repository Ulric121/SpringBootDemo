package com.example.springboot.lambda;

/**
 * Description:@FunctionalInterface用来定义函数式接口
 * 只有函数式接口才可以使用lambda表达式
 * 函数式接口中只能有一个抽象方法
 * 当接口中只有一个抽象方法的时候，该接口会被推导为函数式接口，比如InvocationHandler接口
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/27
 */
@FunctionalInterface
public interface MyFunction {

    void sayHello();

    /**
     * 接口中使用default关键字定义非抽象方法
     */
    default void sayHello2() {
    }
}
