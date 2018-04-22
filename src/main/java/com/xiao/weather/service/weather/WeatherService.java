package com.xiao.weather.service.weather;

import com.xiao.weather.entity.location.WechatLocation;

/**
 * @author xiao_elevener
 */
public interface WeatherService {

    /**
     * 获取位置信息当前天气
     * @param wechatLocation
     * @return
     */
    String getNowWeather(WechatLocation wechatLocation);

}
