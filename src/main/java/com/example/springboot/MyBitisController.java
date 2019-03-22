package com.example.springboot;

import com.example.springboot.common.MyResponse;
import com.example.springboot.mybatis.entity.User;
import com.example.springboot.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/8
 */
@RestController
@RequestMapping("/test")
public class MyBitisController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public MyResponse<List<User>> getAll() {
        List<User> userList = userService.getAll();
        MyResponse<List<User>> response = new MyResponse<>();
        return response.success(userList);
    }

    @RequestMapping(value = "/getOne/{id}", method = RequestMethod.GET)
    public MyResponse<User> getOne(@PathVariable("id") int id) {
        User user = userService.getOne(id);
        MyResponse<User> response = new MyResponse<>();
        return response.success(user);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public MyResponse insert(@RequestBody User user) {
        try {
            userService.insert(user);
            return new MyResponse().success();
        } catch (Exception e) {
            return new MyResponse().failure("新增出错：" + e.getMessage());
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public MyResponse update(@RequestBody User user) {
        try {
            userService.update(user);
            return new MyResponse().success();
        } catch (Exception e) {
            return new MyResponse().failure("修改出错：" + e.getMessage());
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public MyResponse delete(@RequestParam("id") int id) {
        try {
            userService.delete(id);
            return new MyResponse().success();
        } catch (Exception e) {
            return new MyResponse().failure("删除出错：" + e.getMessage());
        }
    }
}
