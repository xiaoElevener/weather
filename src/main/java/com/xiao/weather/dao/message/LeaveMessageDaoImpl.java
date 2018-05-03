package com.xiao.weather.dao.message;

import com.xiao.weather.dao.core.BaseDao;
import com.xiao.weather.entity.message.LeaveMessage;
import org.springframework.stereotype.Repository;

/**
 * @author xiao_elevener
 */
@Repository
public class LeaveMessageDaoImpl extends BaseDao<LeaveMessage> implements LeaveMessageDao {

    private final String GET_TODAY_COUNT = "getTodayCount";

    @Override
    public Integer getTodayCount() {
        return sqlSession.selectOne(getStatementPrefix() + GET_TODAY_COUNT);
    }
}


