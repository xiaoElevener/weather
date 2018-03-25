package com.xiao.weather.controller;

import com.xiao.weather.constant.MsgType;
import com.xiao.weather.service.WechatEventService;
import com.xiao.weather.vo.WechatMessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.bean.message.message.Message;
import weixin.popular.util.XMLConverUtil;

/**
 * @author xiao_elevener
 * @date 2018-03-24 17:04
 */
@RestController
@Slf4j
public class WechatController {

    @Autowired
    private WechatEventService wechatEventService;

    @RequestMapping("/wechat")
    public String responseToWechat(@RequestBody String content) {
        log.info("content={}", content);
        EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, content);
        if (eventMessage.getMsgType().equals(MsgType.EVENT.getType())) {
            Message message = wechatEventService.handleEventMessage(eventMessage);
            WechatMessageResponse<Message> response = new WechatMessageResponse<>();
            response.setMessage(message);
            log.info("response={}", response);
            return XMLConverUtil.convertToXML(response);
        }
        return "success";
    }


}
