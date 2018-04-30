package com.xiao.weather.service.wechat.messageHandler.event;

import weixin.popular.bean.message.EventMessage;

/**
 * @author xiao_elevener
 */
public interface EventHandler {
    /**
     * 获取事件类型
     *
     * @return
     */
    String getEventType();

    /**
     * 处理事件
     *
     * @param eventMessage
     * @return
     */
    EventMessage handleEvent(EventMessage eventMessage);
}
