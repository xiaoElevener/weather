package com.xiao.weather.service.statistical;

import com.xiao.weather.common.vo.systemStatistical.SystemStatisticalVo;
import com.xiao.weather.dao.dealhistory.DealHistoryDao;
import com.xiao.weather.dao.message.LeaveMessageDao;
import com.xiao.weather.dao.user.UserDao;
import com.xiao.weather.service.core.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiao_elevener
 * @date 2018-05-03 3:01
 */
@Service
public class StatisticalServiceImpl extends AbstractServiceImpl implements StatisticalService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LeaveMessageDao leaveMessageDao;

    @Autowired
    private DealHistoryDao dealHistoryDao;

    @Override
    public SystemStatisticalVo getSystemStatisticalVo() {
        SystemStatisticalVo result = new SystemStatisticalVo();
        result.setWechatUser(userDao.wechatUser());
        result.setWechatMessage(leaveMessageDao.getTodayCount());
        result.setDealCount(dealHistoryDao.getTodayCount());
        return result;
    }
}
