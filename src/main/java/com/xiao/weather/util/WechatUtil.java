package com.xiao.weather.util;


import com.xiao.weather.config.WechatConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import weixin.popular.api.MenuAPI;
import weixin.popular.api.TokenAPI;
import weixin.popular.bean.BaseResult;
import weixin.popular.bean.token.Token;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 微信工具类
 *
 * @author xiao_elevener
 * @date 2018-03-24 14:36
 */
@Component
@Slf4j
public class WechatUtil {

    @Autowired
    private WechatConfig wechatConfig;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public static final String ACCESS_TOKEN_KEY = "accessToken";

    /**
     * 设置过期时间为7200s
     */
    public static final long EXPIRE_TIME = 7200;

    public String getAccessToken() {
        String accessToekn = redisTemplate.opsForValue().get(ACCESS_TOKEN_KEY);
        if (accessToekn != null) {
            return accessToekn;
        }
        Token token = TokenAPI.token(wechatConfig.getAppID(), wechatConfig.getAppSecret());
        redisTemplate.opsForValue().set(ACCESS_TOKEN_KEY, token.getAccess_token(), EXPIRE_TIME, TimeUnit.SECONDS);
        return token.getAccess_token();
    }


    public void createMenu() {
        String menuJson = null;
        File menu = new File("G:\\IDEA_Project\\newseawin\\weather\\src\\main\\resources\\static\\wechat\\menu.json");
        try {
            menuJson = FileUtils.readFileToString(menu, Charsets.UTF_8);
        } catch (IOException exception) {
            log.error("读取menu.json错误，{}", exception);
        }
        BaseResult baseResult = MenuAPI.menuCreate(getAccessToken(), menuJson);
        if (baseResult.isSuccess()) {
            log.info("微信菜单创建成功!");
        } else {
            log.error("微信菜单创建失败，原因:{}  {}", baseResult.getErrcode(), baseResult.getErrmsg());
        }
    }
}