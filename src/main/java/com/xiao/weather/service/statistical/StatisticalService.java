package com.xiao.weather.service.statistical;

import com.xiao.weather.common.vo.systemStatistical.SystemStatisticalVo;

/**
 * 统计
 *
 * @author xiao_elevener
 * @date 2018-05-03 2:59
 */
public interface StatisticalService {

    /**
     * 获取统计数据
     * @return
     */
    SystemStatisticalVo getSystemStatisticalVo();

}
