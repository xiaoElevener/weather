package com.xiao.weather.constant;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum Api {
    /**
     * 天气接口
     */
    WEATHER_API("https://api.seniverse.com/v3/weather/now.json");
    
    private String url;

    public String getUrl(){
        return this.url;
    }

}
