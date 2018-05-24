package com.xiao.weather.service.wechat.sns;


/**
 * 微信网页授权
 *
 * @author xiao_elevener
 * @date 2018-05-13 18:26
 */
public interface SnsService {

    /**
     * 获取openId
     *
     * @param code
     * @return
     */
    String getOpenId(String code);
}
