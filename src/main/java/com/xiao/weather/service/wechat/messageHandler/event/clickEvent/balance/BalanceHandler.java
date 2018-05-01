package com.xiao.weather.service.wechat.messageHandler.event.clickEvent.balance;

import com.xiao.weather.common.constant.EventKey;
import com.xiao.weather.dao.account.AccountDao;
import com.xiao.weather.dao.user.UserDao;
import com.xiao.weather.entity.account.Account;
import com.xiao.weather.entity.user.User;
import com.xiao.weather.service.wechat.messageHandler.event.clickEvent.EventKeyHandler;
import com.xiao.weather.util.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weixin.popular.bean.message.EventMessage;

/**
 * 余额查询
 *
 * @author xiao_elevener
 * @date 2018-05-01 11:43
 */
@Component
public class BalanceHandler implements EventKeyHandler {

    private static final EventKey BALANCE = EventKey.BALANCE;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private WechatUtil wechatUtil;

    @Override
    public String getEventKey() {
        return BALANCE.toString();
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
        Account account = accountDao.findByUserId(user.getId());
        responseTextMessage.setContent("余额:" + account.getBalance());
        return responseTextMessage;
    }
}
