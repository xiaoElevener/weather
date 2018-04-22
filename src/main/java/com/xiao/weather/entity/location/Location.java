package com.xiao.weather.entity.location;

import lombok.Data;

/**
 * 位置信息
 *
 * @author Administrator
 * @create 2018-03-20 12:03
 */
@Data
public class Location {
    /**
     * 位置Id
     */
    private String id;

    /**
     * 地名
     */
    private String name;

    /**
     * 国家编号
     */
    private String country;

    /**
     *  城市树路径
     */
    private String path;

    /**
     * 时区
     */
    private String timezone;

    /**
     * 时差
     */
    private String timezoneOffset;

}
