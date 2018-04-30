package com.xiao.weather.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Lin LingXiao
 * 微信公众号 点击事件对应的key
 */
@Getter
@AllArgsConstructor
public enum EventKey {

    /**
     * 点击菜单获取当前天气情况
     */
    NOW_WEATHER,

    /**
     * 余额查询
     */
    BALANCE,

    /**
     * 账号绑定
     */
    BINDING,

    /**
     * 忘记密码
     */
    FORGET,

    /**
     * 交易查询
     */
    DEAL_HISTORY

}
