package com.example.springboot.mybatis.mapper;

import com.example.springboot.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 查询全部
     *
     * @return 对象列表
     */
    List<User> getAll();

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return user对象
     */
    User getOne(@Param("id") int id);

    /**
     * 新增
     *
     * @param user 对象
     */
    void insert(User user);

    /**
     * 修改
     *
     * @param user 对象
     */
    void update(User user);

    /**
     * 删除
     *
     * @param id 主键
     */
    void delete(@Param("id") int id);
}
