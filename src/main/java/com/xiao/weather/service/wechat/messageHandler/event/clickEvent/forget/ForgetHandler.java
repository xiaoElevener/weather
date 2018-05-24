package com.xiao.weather.service.wechat.messageHandler.event.clickEvent.forget;

import com.xiao.weather.common.constant.EventKey;
import com.xiao.weather.dao.predefinedcode.PredefinedCodeDao;
import com.xiao.weather.dao.user.UserDao;
import com.xiao.weather.entity.predefinedcode.PredefinedCode;
import com.xiao.weather.entity.user.User;
import com.xiao.weather.service.wechat.messageHandler.event.clickEvent.EventKeyHandler;
import com.xiao.weather.util.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weixin.popular.bean.message.EventMessage;

/**
 * @author xiao_elevener
 * @date 2018-05-16 14:42
 * 忘记密码
 */
@Component
public class ForgetHandler implements EventKeyHandler {

    private static final EventKey FORGET = EventKey.FORGET;

    private final String RESET_PASSWORD = "reset_password";

    @Autowired
    private WechatUtil wechatUtil;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PredefinedCodeDao predefinedCodeDao;

    @Override
    public String getEventKey() {
        return FORGET.toString();
    }

    @Override
    public EventMessage handleEventKey(EventMessage eventMessage) {
        EventMessage responseTextMessage = wechatUtil.createResponseTextMessage(eventMessage);
        String openId = eventMessage.getFromUserName();
        User user = userDao.findUserByOpenId(openId);
        if (user == null) {
            responseTextMessage.setContent("您未绑定账户，请先点击菜单上的绑定用户，按照提示操作!");
            return responseTextMessage;
        }
        resetPassword(user);
        responseTextMessage.setContent("密码重置成功，密码为:" + user.getPassword());
        return responseTextMessage;
    }

    /**
     * 重置密码
     *
     * @param user
     */
    private void resetPassword(User user) {
        PredefinedCode predefinedCode = predefinedCodeDao.findByCode(RESET_PASSWORD);
        String password = predefinedCode.getValue();
        user.setPassword(password);
        userDao.update(user);
    }
}
