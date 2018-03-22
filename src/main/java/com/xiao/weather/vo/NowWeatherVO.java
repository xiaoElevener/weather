package com.xiao.weather.vo;

import com.xiao.weather.entity.Location;
import com.xiao.weather.entity.Weather;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @create 2018-03-20 12:04
 */
@Data
public class NowWeatherVO implements Serializable{
    /**
     * 地址
     */
    private Location location;

    /**
     * 天气
     */
    private Weather weather;

    /**
     * 上次更新时间
     */
    private Date lastUpdate;
}
