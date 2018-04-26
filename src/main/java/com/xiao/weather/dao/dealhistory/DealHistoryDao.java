package com.xiao.weather.dao.dealhistory;

import com.xiao.weather.common.vo.dealhistory.DailyStatisticalVo;
import com.xiao.weather.dao.core.Dao;
import com.xiao.weather.entity.dealhistory.DealHistory;

import java.util.List;

/**
 * @author xiao_elevener
 */
public interface DealHistoryDao extends Dao<DealHistory> {

    List<DailyStatisticalVo> getDailyStatistical(Integer day);

}



