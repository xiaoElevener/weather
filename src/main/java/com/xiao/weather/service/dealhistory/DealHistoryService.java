package com.xiao.weather.service.dealhistory;

import com.xiao.weather.common.vo.dealhistory.DealHistoryVo;

/**
 * @author xiao_elevener
 */
public interface DealHistoryService {

    /**
     * 创建交易记录
     *
     * @param dealHistoryVo
     */
    void create(DealHistoryVo dealHistoryVo);

}
