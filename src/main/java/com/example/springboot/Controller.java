package com.example.springboot;

import com.example.springboot.aop.Log;
import com.example.springboot.redis.Person;
import com.example.springboot.redis.PersonDao;
import com.example.springboot.threadlocal.WebContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/8
 */
@RestController
@RequestMapping("/test")
public class Controller {

    @Value("${platform.parameter.author.name}")
    private String authorName;

    @Autowired
    private PersonDao personDao;

    @Log(module = "用户管理", operation = "新增")
    @RequestMapping(value = "/fun1", method = RequestMethod.GET)
    public String fun1(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("age") int age) {
        Person person = new Person(id, name, age);
        personDao.setPerson(person);
        return "success";
    }

    @Log(module = "订单管理", operation = "查询")
    @RequestMapping(value = "/fun2", method = RequestMethod.GET)
    public Object fun2(@RequestParam("id") String id) {
        Person person = (Person) WebContextHolder.getSession().getAttribute("user");
        System.out.println("测试ThreadLocal的使用：" + person);
        System.out.println("测试@Value注解的使用：" + authorName);
        return personDao.getString(id);
    }
}
