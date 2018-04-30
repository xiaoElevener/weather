package com.xiao.weather.service.wechat.messageHandler;

import com.xiao.weather.service.wechat.messageHandler.event.EventHandlerCenter;
import com.xiao.weather.service.wechat.messageHandler.text.TextMessageHandler;
import com.xiao.weather.util.WechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weixin.popular.bean.message.EventMessage;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 消息处理中心
 *
 * @author xiao_elevener
 * @date 2018-04-21 9:10
 */
@Component
@Slf4j
public class MessageHandlerCenter {

    @Autowired
    private WechatUtil wechatUtil;

    private List<MessageHandler> messageHandlerList = new LinkedList<>();

    @Autowired
    private EventHandlerCenter eventHandlerCenter;

    @Autowired
    private TextMessageHandler textMessageHandler;

    /**
     * 微信消息处理器初始化,现在能够处理文本和事件
     */
    @PostConstruct
    public void init() {
        messageHandlerList.add(eventHandlerCenter);
        messageHandlerList.add(textMessageHandler);
    }

    public EventMessage handleEventMessage(EventMessage eventMessage) {
        Iterator<MessageHandler> iterator = messageHandlerList.iterator();
        while (iterator.hasNext()) {
            MessageHandler messageHandler = iterator.next();
            if (messageHandler.getMsgType().equalsIgnoreCase(eventMessage.getMsgType())) {
                return messageHandler.handleMessage(eventMessage);
            }
        }
        log.warn("无此消息类型处理器，eventMessage={}", eventMessage.toString());
        return null;
    }

}
