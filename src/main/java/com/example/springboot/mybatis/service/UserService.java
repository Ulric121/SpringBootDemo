package com.example.springboot.mybatis.service;

import com.example.springboot.mybatis.entity.User;
import com.example.springboot.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/22
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAll() {
        List<User> userList = userMapper.getAll();
        return userList;
    }

    public User getOne(int id) {
        User user = userMapper.getOne(id);
        return user;
    }

    public void insert(User user) {
        userMapper.insert(user);
        System.out.println("新增的主键值是：" + user.getId());
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public void delete(int id) {
        userMapper.delete(id);
    }
}
