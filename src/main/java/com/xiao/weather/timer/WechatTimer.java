package com.xiao.weather.timer;

import com.xiao.weather.config.WechatConfig;
import com.xiao.weather.util.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import weixin.popular.api.TokenAPI;
import weixin.popular.bean.token.Token;

import java.util.concurrent.TimeUnit;

/**
 * @author xiao_elevener
 * @date 2018-03-24 15:31
 */
@Component
public class WechatTimer {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private WechatConfig wechatConfig;

    @Scheduled(cron = "0 0 0/2 * * ? ")
    public void setAccessToken() {
        Token token = TokenAPI.token(wechatConfig.getAppID(), wechatConfig.getAppSecret());
        redisTemplate.opsForValue().set(WechatUtil.ACCESS_TOKEN_KEY, token.getAccess_token(), WechatUtil.EXPIRE_TIME, TimeUnit.SECONDS);
    }

}
