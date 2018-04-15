package com.xiao.weather.service;

public interface WeatherService {

    /**
     * 获取位置信息当前天气
     *
     * @param location
     * @return
     */
    String getNowWeather(String location);

}
