package com.xiao.weather.dao.dealhistory;

import com.xiao.weather.common.vo.dealhistory.DailyStatisticalVo;
import com.xiao.weather.dao.core.BaseDao;
import com.xiao.weather.entity.dealhistory.DealHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}


