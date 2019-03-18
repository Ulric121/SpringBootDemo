package com.example.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/11
 */
@Repository
public class PersonDao {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void setPerson(Person person) {
        redisTemplate.opsForValue().set("123", person);
    }

    public Person getPerson(String id) {
        return (Person) redisTemplate.opsForValue().get(id);
    }

}
