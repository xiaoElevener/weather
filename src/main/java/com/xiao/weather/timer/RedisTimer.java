package com.xiao.weather.timer;

import com.xiao.weather.service.wechat.messageHandler.MessageHandlerCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * redis定时器
 *
 * @author xiao_elevener
 * @date 2018-05-01 22:30
 */
@Component
@Slf4j
public class RedisTimer {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 过期时间10s
     */
    private final long EXPIRE_TIME = 10 * 1000;

    /**
     * 定时移除msgId
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void expireSet() {
        redisTemplate.opsForZSet().removeRangeByScore(MessageHandlerCenter.MSG_KEY, 0, System.currentTimeMillis() - EXPIRE_TIME);
    }
}
