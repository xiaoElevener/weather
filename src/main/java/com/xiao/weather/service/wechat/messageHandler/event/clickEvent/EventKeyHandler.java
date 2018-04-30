package com.xiao.weather.service.wechat.messageHandler.event.clickEvent;

import weixin.popular.bean.message.EventMessage;

/**
 * 点击事件key接口
 *
 * @author xiao_elevener
 */
public interface EventKeyHandler {

    /**
     * 获取eventKey
     *
     * @return
     */
    String getEventKey();

    /**
     * 处理事件
     *
     * @param eventMessage
     * @return
     */
    EventMessage handleEventKey(EventMessage eventMessage);

}
