package com.xiao.weather.service.dealhistory;

import com.xiao.weather.common.so.dealhistory.DealHistorySo;
import com.xiao.weather.common.vo.account.AccountVo;
import com.xiao.weather.common.vo.dealhistory.DailyStatisticalVo;
import com.xiao.weather.common.vo.dealhistory.DealHistoryVo;

import java.util.List;

/**
 * @author xiao_elevener
 */
public interface DealHistoryService {

    /**
     * 创建交易记录
     *
     * @param dealHistoryVo
     */
    AccountVo create(DealHistoryVo dealHistoryVo);


    /**
     * 获取近期交易统计
     *
     * @return
     */
    List<DailyStatisticalVo> getDailyStatistical();

    /**
     * 获取用户最近交易记录
     *
     * @param userId
     * @return
     */
    List<DealHistoryVo> getRecentlyDealHistory(Long userId);


    /**
     * 条件分页换取交易记录
     *
     * @param dealHistorySo
     * @return
     */
    List<DealHistoryVo> findDealHistoryVosBySo(DealHistorySo dealHistorySo);


    /**
     * 条件分页换取交易记录数量
     * @param dealHistorySo
     * @return
     */
    Integer countByDealHistorySo(DealHistorySo dealHistorySo);
}
