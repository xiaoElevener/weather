package com.xiao.weather.service.wechat.eventHandler;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weixin.popular.bean.message.EventMessage;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

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
    private TextMessageHandler textMessageHandler;

    @Autowired
    private  EventMessageHandler eventMessageHandler;

    private List<MessageHandler> messageHandlerList = new LinkedList<>();



    public MessageHandlerCenter(){
        messageHandlerList.add(textMessageHandler);
        messageHandlerList.add(eventMessageHandler);
    }

    public EventMessage handlEventMessage(EventMessage eventMessage){
        Iterator<MessageHandler> iterator = messageHandlerList.iterator();
        while (iterator.hasNext()){
            MessageHandler messageHandler =  iterator.next();
            if(messageHandler.getEventTpye().equalsIgnoreCase(eventMessage.getEvent())){
               return messageHandler.handleEvent(eventMessage);
            }
        }
        log.warn("无此消息类型处理器，eventMessage={}", JSONUtils.toJSONString(eventMessage));
        return null;
    }

}
