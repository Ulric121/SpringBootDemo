package com.example.springboot.mybatis.mapper;

import com.example.springboot.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description:
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/22
 */
@Mapper
public interface UserMapper {

    /**
     * 根据主键查询
     *
     * @param id id
     * @return user对象
     */
    User select(int id);
}
