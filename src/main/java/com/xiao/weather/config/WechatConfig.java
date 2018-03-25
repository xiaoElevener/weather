package com.xiao.weather.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信配置类
 *
 * @author xiao_elevener
 * @date 2018-03-24 14:37
 */
@Data
@ConfigurationProperties(prefix = "wechat")
@Component
public class WechatConfig {

    private String AppID;

    private String AppSecret;

    private String createMenu;

    private String myUserName;
}
