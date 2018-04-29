package com.xiao.weather.service.message;

import com.xiao.weather.common.so.message.LeaveMessageSo;
import com.xiao.weather.common.vo.message.LeaveMessageVo;

import java.util.List;

/**
 * @author xiao_elevener
 */
public interface LeaveMessageService {
    /**
     * 条件分页查询
     *
     * @param leaveMessageSo
     * @return
     */
    List<LeaveMessageVo> findLeaveMessagesBySo(LeaveMessageSo leaveMessageSo);

    /**
     * 条件分页查询数量
     *
     * @param leaveMessageSo
     * @return
     */
    Integer countByLeaveMessageSo(LeaveMessageSo leaveMessageSo);
}
