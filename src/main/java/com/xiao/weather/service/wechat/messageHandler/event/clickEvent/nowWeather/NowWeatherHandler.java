package com.xiao.weather.service.wechat.messageHandler.event.clickEvent.nowWeather;

import com.xiao.weather.common.constant.EventKey;
import com.xiao.weather.entity.location.WechatLocation;
import com.xiao.weather.service.weather.WeatherService;
import com.xiao.weather.service.wechat.messageHandler.event.clickEvent.EventKeyHandler;
import com.xiao.weather.service.wechat.messageHandler.event.location.LocationEventHandler;
import com.xiao.weather.util.RedisUtil;
import com.xiao.weather.util.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weixin.popular.bean.message.EventMessage;

/**
 * 天气查询点击事件
 *
 * @author xiao_elevener
 * @date 2018-04-30 19:24
 */
@Component
public class NowWeatherHandler implements EventKeyHandler {

    private static final EventKey NOW_WEATHER = EventKey.NOW_WEATHER;

    @Autowired
    private WechatUtil wechatUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WeatherService weatherService;

    @Override
    public String getEventKey() {
        return NOW_WEATHER.toString();
    }


    @Override
    public EventMessage handleEventKey(EventMessage eventMessage) {
        EventMessage responseMessage = wechatUtil.createResponseTextMessage(eventMessage);
        WechatLocation location = (WechatLocation) redisUtil.get(LocationEventHandler.LOCATION_KEY_PREFIX + eventMessage.getFromUserName());
        if (location == null) {
            responseMessage.setContent("无法获取位置信息,请提供位置服务");
            return responseMessage;
        }
        responseMessage.setContent(weatherService.getNowWeather(location));
        return responseMessage;
    }
}
