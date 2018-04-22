package com.xiao.weather.common.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 心知天气配置文件
 *
 * @author xiao_elevener
 * @date 2018-03-19 23:22
 */
@Data
@ConfigurationProperties(prefix = "xinzhi")
@Component
public class XinZhiProperties {

    private String uid ;

    private  String key ;

    private String weatherApi ;
}
