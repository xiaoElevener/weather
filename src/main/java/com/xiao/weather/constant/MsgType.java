package com.xiao.weather.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiao_elevener
 * @date 2018-03-25 0:00
 */
@AllArgsConstructor
@Getter
public enum MsgType {

    /**
     * 事件
     */
    EVENT("event");

    String type;

}
