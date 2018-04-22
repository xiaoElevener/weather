package com.xiao.weather.service.wechat.eventHandler;

import com.xiao.weather.common.constant.MsgType;
import com.xiao.weather.util.WechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weixin.popular.bean.message.EventMessage;

/**
 * 文本事件处理
 *
 * @author xiao_elevener
 * @date 2018-04-21 1:25
 */
@Component
@Slf4j
public class TextMessageHandler implements MessageHandler {

    private final MsgType EVENT_TYPE = MsgType.TEXT;

    @Autowired
    private WechatUtil wechatUtil;

    @Override
    public String getEventType() {
        return this.EVENT_TYPE.toString();
    }

    @Override
    public EventMessage handleMessage(EventMessage eventMessage) {
        EventMessage responseMessage = wechatUtil.createResponseTextMessage(eventMessage);
        responseMessage.setContent("谢谢反馈");
        return responseMessage;
    }
}
