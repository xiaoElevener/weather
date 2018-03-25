package com.xiao.weather.config;

import com.xiao.weather.WeatherApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;


public class RedisCacheConfigTest extends WeatherApplicationTests{

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;


    @Test
    public void test(){
        ValueOperations<Object, Object> ops = redisTemplate.opsForValue();
        ops.set("key:user1","user1");

        System.out.println(ops.get("key:user1"));
    }

}