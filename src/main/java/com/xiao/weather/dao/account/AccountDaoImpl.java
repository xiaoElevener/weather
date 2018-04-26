package com.xiao.weather.dao.account;

import com.xiao.weather.dao.core.BaseDao;
import com.xiao.weather.entity.account.Account;
import org.springframework.stereotype.Repository;

/**
 * @author xiao_elevener
 */
@Repository
public class AccountDaoImpl extends BaseDao<Account> implements AccountDao {

    private final String FIND_BY_USER_ID = "findByUserId";

    @Override
    public Account findByUserId(Long userId) {
        return sqlSession.selectOne(getStatementPrefix() + FIND_BY_USER_ID, userId);
    }
}


