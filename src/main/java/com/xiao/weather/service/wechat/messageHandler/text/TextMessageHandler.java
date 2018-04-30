package com.xiao.weather.service.wechat.messageHandler.text;

import com.xiao.weather.common.constant.MsgType;
import com.xiao.weather.dao.message.LeaveMessageDao;
import com.xiao.weather.dao.predefinedcode.PredefinedCodeDao;
import com.xiao.weather.entity.message.LeaveMessage;
import com.xiao.weather.service.wechat.messageHandler.MessageHandler;
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
@Slf4j
@Component
public class TextMessageHandler implements MessageHandler {

    private final MsgType MESSAGE_TYPE = MsgType.TEXT;

    private final String LEAVE_MESSAGE = "留言";

    private final String WECHAT_DEFAULT_FEEDBACK = "wechat_default_feedback";

    private final String WECHAT_SUCCESS_FEEDBACK = "wechat_success_message";

    @Autowired
    private WechatUtil wechatUtil;

    @Autowired
    private LeaveMessageDao leaveMessageDao;

    @Autowired
    private PredefinedCodeDao predefinedCodeDao;

    @Override
    public String getMsgType() {
        return this.MESSAGE_TYPE.toString();
    }

    @Override
    public EventMessage handleMessage(EventMessage eventMessage) {
        EventMessage responseMessage = wechatUtil.createResponseTextMessage(eventMessage);
        if (eventMessage.getContent().startsWith(LEAVE_MESSAGE)) {
            saveMessage(eventMessage);
            responseMessage.setContent(predefinedCodeDao.findByCode(WECHAT_SUCCESS_FEEDBACK).getValue());
        } else {
            responseMessage.setContent(predefinedCodeDao.findByCode(WECHAT_DEFAULT_FEEDBACK).getValue());
        }
        return responseMessage;
    }

    /**
     * 保存消息
     *
     * @param eventMessage
     */
    public void saveMessage(EventMessage eventMessage) {
        LeaveMessage message = new LeaveMessage();
        message.setContent(eventMessage.getContent());
        message.setOpenId(eventMessage.getFromUserName());
        leaveMessageDao.insert(message);
    }

}
