package com.example.springboot;

import com.example.springboot.aop.Log;
import com.example.springboot.redis.Person;
import com.example.springboot.redis.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
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
public class Controller {

    @Autowired
    private PersonDao personDao;

    @Log
    @RequestMapping(value = "/fun", method = RequestMethod.GET)
    public String fun(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("age") int age) {
        Person person = new Person(id, name, age);
        personDao.setPerson(person);
        return person.toString();
    }
}
