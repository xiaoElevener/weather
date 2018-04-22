package com.xiao.weather.common.vo.wechat;

import lombok.Data;

/**
 * @author xiao_elevener
 * @date 2018-03-25 0:50
 * 被动回复用户消息
 */
@Data

public class WechatMessageResponse {



    private Integer CreateTime;

    public WechatMessageResponse() {
        Long createTime = System.currentTimeMillis() / 1000;
        this.CreateTime = createTime.intValue();
    }

}
