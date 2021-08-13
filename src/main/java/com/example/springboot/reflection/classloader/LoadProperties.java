package com.example.springboot.reflection.classloader;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Description:
 *
 * @author yangtao
 * @version 0.0.1
 * @since 2021/8/13
 */
public class LoadProperties {

    @Test
    public void test1() throws Exception {
        Properties properties = new Properties();
        // 文件默认在当前的module下
        FileInputStream inputStream = new FileInputStream("src/main/resources/reflection.properties");
        properties.load(inputStream);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        System.out.println(user + ":" + password);
    }

    @Test
    public void test2() throws Exception{
        Properties properties = new Properties();
        ClassLoader classLoader = LoadProperties.class.getClassLoader();
        // 文件默认在当前module的resources下
        InputStream inputStream = classLoader.getResourceAsStream("reflection.properties");
        properties.load(inputStream);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        System.out.println(user + ":" + password);
    }
}
