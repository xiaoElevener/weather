package com.xiao.weather.service.wechat.messageHandler.event.clickEvent.dealHistory;

import com.xiao.weather.common.constant.DealType;
import com.xiao.weather.common.constant.EventKey;
import com.xiao.weather.common.vo.dealhistory.DealHistoryVo;
import com.xiao.weather.dao.user.UserDao;
import com.xiao.weather.entity.user.User;
import com.xiao.weather.service.dealhistory.DealHistoryService;
import com.xiao.weather.service.wechat.messageHandler.event.clickEvent.EventKeyHandler;
import com.xiao.weather.util.DateUtil;
import com.xiao.weather.util.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weixin.popular.bean.message.EventMessage;

import java.util.List;

/**
 * 交易历史记录处理器
 *
 * @author xiao_elevener
 * @date 2018-04-30 23:47
 */
@Component
public class DealHistoryHandler implements EventKeyHandler {

    private static final EventKey DEAL_HISTORY = EventKey.DEAL_HISTORY;


    @Autowired
    private UserDao userDao;

    @Autowired
    private WechatUtil wechatUtil;

    @Autowired
    private DealHistoryService dealHistoryService;


    @Override
    public String getEventKey() {
        return DEAL_HISTORY.toString();
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
        List<DealHistoryVo> dealHistoryVoList = dealHistoryService.getRecentlyDealHistory(user.getId());
        responseTextMessage.setContent(btfList(dealHistoryVoList));
        return responseTextMessage;
    }

    /**
     * 格式化输出
     *
     * @return
     */
    private String btfList(List<DealHistoryVo> dealHistoryVoList) {
        StringBuilder string = new StringBuilder();
        dealHistoryVoList.forEach(dealHistoryVo -> {
            string.append("日期:" + DateUtil.date2LocalDateTime(dealHistoryVo.getCreatedTime()) + " ");
            if (dealHistoryVo.getDealType().equals(DealType.CONSUME)) {
                string.append("消费了");
            } else {
                string.append("充值了");
            }
            string.append(dealHistoryVo.getMoney()).append("元").append("\n");
        });
        return string.toString();
    }
}
