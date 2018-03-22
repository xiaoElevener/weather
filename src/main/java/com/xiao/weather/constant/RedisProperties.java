package com.xiao.weather.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @create 2018-03-22 14:55
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisProperties {
    private int database;

    private String host;

    private String password;

    private int port;

    private int timeout;


    private RedisProperties.Pool pool = new Pool();

    @Data
    private static class Pool {

        private int maxIdle = 8;

        private int minIdle = 0;

        private int maxActive = 8;

        private int maxWait = -1;
    }
}
