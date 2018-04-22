package com.xiao.weather.service.wechat.eventHandler;

import com.xiao.weather.common.constant.EventKey;
import com.xiao.weather.common.constant.MsgType;
import com.xiao.weather.service.weather.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weixin.popular.bean.message.EventMessage;

/**
 * 事件消息处理器
 *
 * @author xiao_elevener
 * @date 2018-04-21 9:09
 */
@Component
@Slf4j
public class EventMessageHandler implements MessageHandler {

    /**
     * 处理消息的类型
     */
    private final MsgType EVENT_TYPE = MsgType.EVENT;

    @Autowired
    private WeatherService weatherService;

    @Override
    public String getEventType() {
        return this.EVENT_TYPE.toString();
    }

    @Override
    public EventMessage handleMessage(EventMessage eventMessage) {
        if(eventMessage.getEventKey().equalsIgnoreCase(EventKey.NOW_WEATHER.toString())){
            weatherService.getNowWeather()
        }
        return null;
    }
}
