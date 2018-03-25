package com.xiao.weather.service;

import com.xiao.weather.vo.NowWeatherVO;

public interface WeatherService {

    /**
     * 获取位置信息当前天气
     *
     * @param location
     * @return
     */
    NowWeatherVO getNowWeather(String location);

}
