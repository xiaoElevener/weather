package com.xiao.weather.entity.location;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信用户坐标
 *
 * @author xiao_elevener
 * @date 2018-04-21 17:41
 */
@Data
public class WechatLocation implements Serializable{

    private static final long serialVersionUID = -868313193682043452L;
    /**
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 精度
     */
    private String precision;

}
