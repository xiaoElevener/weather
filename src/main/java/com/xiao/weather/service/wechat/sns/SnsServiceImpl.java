package com.xiao.weather.service.wechat.sns;

import com.xiao.weather.common.constant.WechatProperties;
import com.xiao.weather.common.exception.BizException;
import com.xiao.weather.service.core.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weixin.popular.api.SnsAPI;
import weixin.popular.bean.sns.SnsToken;

/**
 * @author xiao_elevener
 * @date 2018-05-13 18:28
 */
@Service
public class SnsServiceImpl extends AbstractServiceImpl implements SnsService {

    @Autowired
    private WechatProperties wechatProperties;

    @Override
    public String getOpenId(String code) {
        if (code == null) {
            throw new BizException("微信授权失败！");
        }
        SnsToken snsToken = SnsAPI.oauth2AccessToken(wechatProperties.getAppId(), wechatProperties.getAppSecret(), code);
        if (snsToken.getOpenid() == null) {
            throw new BizException("微信授权异常！");
        }
        return snsToken.getOpenid();
    }
}
