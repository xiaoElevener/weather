package com.xiao.weather.service.wechat.messageHandler.event.location;

import com.xiao.weather.common.constant.EventType;
import com.xiao.weather.entity.location.WechatLocation;
import com.xiao.weather.service.wechat.messageHandler.event.EventHandler;
import com.xiao.weather.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weixin.popular.bean.message.EventMessage;

import java.util.concurrent.TimeUnit;

/**
 * 定位事件处理器
 *
 * @author xiao_elevener
 * @date 2018-04-30 18:32
 */
@Component
public class LocationEventHandler implements EventHandler {

    private final static EventType LOCATION = EventType.LOCATION;

    public final static String LOCATION_KEY_PREFIX = "location-";

    /**
     * 默认过期时间
     */
    private final long TIMEOUT = 60;

    private final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String getEventType() {
        return LOCATION.toString();
    }

    @Override
    public EventMessage handleEvent(EventMessage eventMessage) {
        WechatLocation wechatLocation = getWechatLocation(eventMessage);
        //将用户地理位置缓存到redis
        redisUtil.set(LOCATION_KEY_PREFIX + eventMessage.getFromUserName(), wechatLocation, TIMEOUT, TIME_UNIT);
        return null;
    }

    public static WechatLocation getWechatLocation(EventMessage eventMessage) {
        WechatLocation wechatLocation = new WechatLocation();
        wechatLocation.setLatitude(eventMessage.getLatitude());
        wechatLocation.setLongitude(eventMessage.getLongitude());
        wechatLocation.setPrecision(eventMessage.getPrecision());
        return wechatLocation;
    }
}
