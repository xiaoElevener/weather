package com.xiao.weather.vo;

import com.xiao.weather.config.WechatConfig;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xiao_elevener
 * @date 2018-03-25 0:50
 */
@Data
public class WechatMessageResponse<T> {

    @Autowired
    private WechatConfig wechatConfig;

    private T message;

    private String FromUserName = wechatConfig.getMyUserName();

    private Integer CreateTime;

    public WechatMessageResponse() {
        Long createTime = System.currentTimeMillis() / 1000;
        this.CreateTime = createTime.intValue();
    }

}
