package com.xiao.weather.service.wechat.eventHandler;


import weixin.popular.bean.message.EventMessage;

/**
 * 事件类型
 *
 * @author xiao_elevener
 * @date 2018-04-21 1:08
 */
public interface MessageHandler {


    /**
     * 获取事件类型
     * @return
     */
    String getEventType();

    /**
     * 处理事件
     * @return
     */
    EventMessage handleMessage(EventMessage eventMessage);

}
