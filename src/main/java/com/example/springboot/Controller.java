package com.example.springboot;

import com.example.springboot.aop.Log;
import com.example.springboot.common.MyResponse;
import com.example.springboot.mybatis.entity.User;
import com.example.springboot.mybatis.service.UserService;
import com.example.springboot.redis.Person;
import com.example.springboot.redis.PersonDao;
import com.example.springboot.threadlocal.WebContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private UserService userService;

    @Log(module = "用户管理", operation = "新增")
    @RequestMapping(value = "/fun1", method = RequestMethod.GET)
    public Object fun1(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("age") int age) {
        Person person = new Person(id, name, age);
        personDao.setPerson(person);
        return new MyResponse<String>().success();
    }

    @Log(module = "订单管理", operation = "查询")
    @RequestMapping(value = "/fun2", method = RequestMethod.GET)
    public Object fun2(@RequestParam("id") String id) {
        Person person = (Person) WebContextHolder.getSession().getAttribute("user");
        System.out.println("测试ThreadLocal的使用：" + person);
        System.out.println("测试@Value注解的使用：" + authorName);
        MyResponse<String> result = new MyResponse<>();
        return result.success(personDao.getString(id));
    }


    /**
     * 测试mybatis
     *
     * @param id id
     * @return user对象
     */
    @RequestMapping(value = "getUser/{id}", method = RequestMethod.GET)
    public MyResponse<User> getUser(@PathVariable int id) {
        User user = userService.select(id);
        MyResponse<User> result = new MyResponse<>();
        return result.success(user);
    }
}
