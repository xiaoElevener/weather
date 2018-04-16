package com.xiao.weather.common.vo.weather;

import com.xiao.weather.entity.location.Location;
import com.xiao.weather.entity.weather.Weather;
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
