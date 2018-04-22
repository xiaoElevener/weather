package com.xiao.weather.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.omg.PortableInterceptor.LOCATION_FORWARD;

/**
 * @author xiao_elevener
 * @date 2018-03-25 0:00
 * 消息类型
 */
@AllArgsConstructor
@Getter
public enum MsgType {

    /**
     * 事件
     */
    EVENT,

    /**
     * 地理位置
     */
    LOCATION,

    /**
     * 消息
     */
    TEXT,

    /**
     * 图片
     */
    IMAGE


}
