package com.xiao.weather.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author xiao_elevener
 * @date 2018-04-30 18:43
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, final long timeout, final TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 判断sortSet集合中是否存在value
     * 通过排名rank判断，如果为空则不存在这个值
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean sortSetIsMember(String key, Object value) {
        return redisTemplate.opsForZSet().rank(key, value) != null;
    }

    /**
     * 给SortSet集合添加元素
     * 定时器定时删除过期数据
     *
     * @param key
     * @param values
     */
    public void sortSetAdd(String key, Object... values) {
        redisTemplate.opsForZSet().add(key, values, System.currentTimeMillis());
    }
}
