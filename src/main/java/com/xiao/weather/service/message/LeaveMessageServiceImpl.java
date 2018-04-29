package com.xiao.weather.service.message;

import com.xiao.weather.common.so.message.LeaveMessageSo;
import com.xiao.weather.common.vo.message.LeaveMessageVo;
import com.xiao.weather.dao.message.LeaveMessageDao;
import com.xiao.weather.dao.user.UserDao;
import com.xiao.weather.service.core.AbstractServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiao_elevener
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LeaveMessageServiceImpl extends AbstractServiceImpl implements LeaveMessageService {

    @Autowired
    private LeaveMessageDao leaveMessageDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<LeaveMessageVo> findLeaveMessagesBySo(LeaveMessageSo leaveMessageSo) {
        List<LeaveMessageVo> list = leaveMessageDao.selectPaginationVoBySo(leaveMessageSo);
        list.stream().forEach(message -> {
            message.setUserName(userDao.findUserNameByOpenId(message.getOpenId()));
            if (StringUtils.isEmpty(message.getUserName())) {
                message.setUserName("匿名");
            }
        });
        return list;
    }

    @Override
    public Integer countByLeaveMessageSo(LeaveMessageSo leaveMessageSo) {
        return leaveMessageDao.selectCountBySo(leaveMessageSo);
    }
}
