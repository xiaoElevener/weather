package com.xiao.weather.service.wechat.messageHandler.event;

import com.xiao.weather.common.constant.MsgType;
import com.xiao.weather.service.wechat.messageHandler.MessageHandler;
import com.xiao.weather.service.wechat.messageHandler.event.clickEvent.ClickEventCenterHandler;
import com.xiao.weather.service.wechat.messageHandler.event.location.LocationEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weixin.popular.bean.message.EventMessage;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 事件型消息处理中心
 *
 * @author xiao_elevener
 * @date 2018-04-30 18:38
 */
@Component
@Slf4j
public class EventHandlerCenter implements MessageHandler {

    @Autowired
    private LocationEventHandler locationEventHandler;

    @Autowired
    private ClickEventCenterHandler clickEventCenterHandler;

    private List<EventHandler> eventHandlerList = new LinkedList<>();

    @PostConstruct
    public void init() {
        eventHandlerList.add(locationEventHandler);
        eventHandlerList.add(clickEventCenterHandler);
    }

    private final static MsgType EVENT = MsgType.EVENT;

    @Override
    public String getMsgType() {
        return EVENT.toString();
    }

    @Override
    public EventMessage handleMessage(EventMessage eventMessage) {
        Iterator<EventHandler> iterator = eventHandlerList.iterator();
        while (iterator.hasNext()) {
            EventHandler eventHandler = iterator.next();
            if (eventHandler.getEventType().equalsIgnoreCase(eventMessage.getEvent())) {
                return eventHandler.handleEvent(eventMessage);
            }
        }
        log.warn("无此事件类型处理器，eventMessage={}", eventMessage.toString());
        return null;
    }
}
