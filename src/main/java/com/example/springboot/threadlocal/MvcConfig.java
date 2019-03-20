package com.example.springboot.threadlocal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description: springmvc配置类
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/20
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.example.springboot.threadlocal")
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public DemoInterceptor demoInterceptor() {
        return new DemoInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor()).addPathPatterns("/test/**");
    }

}
