package com.xiao.weather.entity;

import lombok.Data;

/**
 * 天气
 *
 * @author xiao_elevener
 * @date 2018-03-19 22:49
 */
@Data
public class Weather {

    /**
     * 天气现象文字
     */
    private String text;

    /**
     * 天气现象代码
     */
    private Integer code;


    /**
     * 气温
     */
    private Integer temperature;

    @Override
    public String toString() {
        return "今天天气:" + text + "  气温:" + temperature + " 摄氏度";
    }
}
