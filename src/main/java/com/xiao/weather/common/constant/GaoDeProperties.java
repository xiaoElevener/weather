package com.xiao.weather.common.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 高德开发者配置
 *
 * @author xiao_elevener
 * @date 2018-04-21 19:13
 */
@Data
@ConfigurationProperties(prefix = "gaode")
@Component
public class GaoDeProperties {


    private String key;

    /**
     * 逆地理编码api
     */
    private String regeoApi;

}
