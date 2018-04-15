package com.xiao.weather.service;

import com.xiao.weather.constant.EventKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.bean.message.message.Message;
import weixin.popular.bean.message.message.TextMessage;

/**
 * @author xiao_elevener
 * @date 2018-03-25 0:13
 */
@Service
public class WechatEventServiceImpl implements WechatEventService {

    @Autowired
    private WeatherService weatherService;

    private static final String LOCATION = "hangzhou";

    @Override
    public Message handleEventMessage(EventMessage eventMessage) {
        if (eventMessage.getEventKey().equals(EventKey.NOW_WEATHER.getEventKey())) {
            String weather = weatherService.getNowWeather(LOCATION);
            return new TextMessage(eventMessage.getFromUserName(), weather);
        }
        return null;
    }
}
