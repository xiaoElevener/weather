package com.xiao.weather.timer;

import com.xiao.weather.common.constant.WechatProperties;
import com.xiao.weather.util.WechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import weixin.popular.api.TokenAPI;
import weixin.popular.bean.token.Token;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;

/**
 * 定时器
 * @author xiao_elevener
 * @date 2018-03-24 15:31
 */
@Component
@Slf4j
public class WechatTimer {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private WechatProperties wechatProperties;

    /**
     * 定时获取微信公众号的accessToken，cron表达式：每个小时执行一次
     * spring中的scheduling的cron表达式不支持 ‘年’的设置
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void setAccessToken() throws ParseException{
        Token token = TokenAPI.token(wechatProperties.getAppId(), wechatProperties.getAppSecret());
        redisTemplate.opsForValue().set(WechatUtil.ACCESS_TOKEN_KEY, token.getAccess_token(), WechatUtil.EXPIRE_TIME, TimeUnit.SECONDS);
    }

}
