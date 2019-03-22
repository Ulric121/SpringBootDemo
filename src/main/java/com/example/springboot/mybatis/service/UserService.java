package com.example.springboot.mybatis.service;

import com.example.springboot.mybatis.entity.User;
import com.example.springboot.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public User select(int id) {
        return userMapper.select(id);
    }

}
