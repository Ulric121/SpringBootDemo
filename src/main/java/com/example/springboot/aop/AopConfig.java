package com.example.springboot.aop;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/8
 */
@Configuration
@Component("com.example.springboot.aop")
@EnableAspectJAutoProxy
public class AopConfig {
}
