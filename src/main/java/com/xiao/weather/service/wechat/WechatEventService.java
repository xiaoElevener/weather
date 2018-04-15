package com.xiao.weather.service.wechat;

import weixin.popular.bean.message.EventMessage;
import weixin.popular.bean.message.message.Message;

/**
 * @author xiao_elevener
 */
public interface WechatEventService {
    Message handleEventMessage(EventMessage eventMessage);
}
