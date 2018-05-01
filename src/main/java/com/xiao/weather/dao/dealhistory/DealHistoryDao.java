package com.xiao.weather.dao.dealhistory;

import com.xiao.weather.common.vo.dealhistory.DailyStatisticalVo;
import com.xiao.weather.common.vo.dealhistory.DealHistoryVo;
import com.xiao.weather.dao.core.Dao;
import com.xiao.weather.entity.dealhistory.DealHistory;

import java.util.List;

/**
 * @author xiao_elevener
 */
public interface DealHistoryDao extends Dao<DealHistory> {

    /**
     * 获取近期统计信息
     *
     * @param day 天数
     * @return
     */
    List<DailyStatisticalVo> getDailyStatistical(Integer day);

    /**
     * 获取该用户的最新账单
     *
     * @param userId 用户id
     * @param limit  条数
     * @return
     */
    List<DealHistoryVo> getDealHistoryByUserId(Long userId, Integer limit);
}



