package com.xiao.weather.service.wechat.messageHandler.event.clickEvent;

import com.xiao.weather.common.constant.EventType;
import com.xiao.weather.service.wechat.messageHandler.event.EventHandler;
import com.xiao.weather.service.wechat.messageHandler.event.clickEvent.nowWeather.NowWeatherHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weixin.popular.bean.message.EventMessage;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 点击事件处理器
 *
 * @author xiao_elevener
 * @date 2018-04-30 18:31
 */
@Component
@Slf4j
public class ClickEventCenterHandler implements EventHandler {

    private static EventType CLICK = EventType.CLICK;


    private List<EventKeyHandler> eventKeyHandlerList = new LinkedList<>();

    @Autowired
    private NowWeatherHandler nowWeatherHandler;

    @PostConstruct
    public void init() {
        eventKeyHandlerList.add(nowWeatherHandler);
    }

    @Override
    public String getEventType() {
        return CLICK.toString();
    }

    @Override
    public EventMessage handleEvent(EventMessage eventMessage) {
        Iterator<EventKeyHandler> iterator = eventKeyHandlerList.iterator();
        while (iterator.hasNext()) {
            EventKeyHandler eventKeyHandler = iterator.next();
            if (eventKeyHandler.getEventKey().equalsIgnoreCase(eventMessage.getEvent())) {
                return eventKeyHandler.handleEventKey(eventMessage);
            }
        }
        log.warn("无此点击事件类型处理器，eventMessage={}", eventMessage.toString());
        return null;
    }
}
