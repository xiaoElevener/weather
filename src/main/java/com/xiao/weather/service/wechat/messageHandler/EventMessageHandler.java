package com.xiao.weather.service.wechat.messageHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.xiao.weather.common.constant.EventKey;
import com.xiao.weather.common.constant.EventType;
import com.xiao.weather.common.constant.MsgType;
import com.xiao.weather.entity.location.WechatLocation;
import com.xiao.weather.service.weather.WeatherService;
import com.xiao.weather.util.WechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    private final MsgType MESSAGE_TYPE = MsgType.EVENT;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WechatUtil wechatUtil;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String getMsgType() {
        return this.MESSAGE_TYPE.toString();
    }

    @Override
    public EventMessage handleMessage(EventMessage eventMessage) {

        EventMessage responseMessage = wechatUtil.createResponseTextMessage(eventMessage);

        //地理位置事件
        if (eventMessage.getEvent().equalsIgnoreCase(EventType.LOCATION.toString())) {
            WechatLocation wechatLocation = getWechatLocation(eventMessage);
            //将用户地理位置缓存到redis
            redisTemplate.opsForValue().set(getLocationKey(eventMessage.getFromUserName()), wechatLocation);
            return null;
        }

        //点击事件
        if (eventMessage.getEvent().equalsIgnoreCase(EventType.CLICK.toString())) {
            log.info("点击事件,{}" + eventMessage);
            if (eventMessage.getEventKey().equalsIgnoreCase(EventKey.NOW_WEATHER.getEventKey())) {
                WechatLocation location = (WechatLocation) redisTemplate.opsForValue().get(getLocationKey(eventMessage.getFromUserName()));
                if (location == null) {
                    responseMessage.setContent("无法获取位置信息,请提供位置服务");
                    return responseMessage;
                }
                responseMessage.setContent(weatherService.getNowWeather(location));
                return responseMessage;
            }
        }

        return null;
    }

    private WechatLocation getWechatLocation(EventMessage eventMessage) {
        WechatLocation wechatLocation = new WechatLocation();
        wechatLocation.setLatitude(eventMessage.getLatitude());
        wechatLocation.setLongitude(eventMessage.getLongitude());
        wechatLocation.setPrecision(eventMessage.getPrecision());
        return wechatLocation;
    }

    private String getLocationKey(String userId) {
        return EventType.LOCATION.toString() + userId;
    }
}
