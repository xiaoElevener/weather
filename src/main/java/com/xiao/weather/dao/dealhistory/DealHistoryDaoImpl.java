package com.xiao.weather.dao.dealhistory;

import com.xiao.weather.common.vo.dealhistory.DailyStatisticalVo;
import com.xiao.weather.common.vo.dealhistory.DealHistoryVo;
import com.xiao.weather.dao.core.BaseDao;
import com.xiao.weather.entity.dealhistory.DealHistory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiao_elevener
 */
@Repository
public class DealHistoryDaoImpl extends BaseDao<DealHistory> implements DealHistoryDao {

    private final String GET_DAILY_STATISTICAL = "getDailyStatistical";

    @Override
    public List<DailyStatisticalVo> getDailyStatistical(Integer day) {
        return sqlSession.selectList(getStatementPrefix() + GET_DAILY_STATISTICAL, day);
    }

    private final String GET_DEAL_HISTORY_BY_USER_ID = "getDealHistoryByUserId";

    @Override
    public List<DealHistoryVo> getDealHistoryByUserId(Long userId, Integer limit) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("limit", limit);
        return sqlSession.selectList(getStatementPrefix() + GET_DEAL_HISTORY_BY_USER_ID, map);
    }


}


