package com.xiao.weather.dao.message;

import com.xiao.weather.dao.core.Dao;
import com.xiao.weather.entity.message.LeaveMessage;

/**
 * @author xiao_elevener
 */
public interface LeaveMessageDao extends Dao<LeaveMessage> {

    /**
     * 获取今日微信留言数
     * @return
     */
    Integer getTodayCount();

}



