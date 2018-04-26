package com.xiao.weather.dao.account;

import com.xiao.weather.dao.core.Dao;
import com.xiao.weather.entity.account.Account;

/**
 * @author xiao_elevener
 */
public interface AccountDao extends Dao<Account> {
    /**
     * 通过用户id查询
     *
     * @param userId
     * @return
     */
    Account findByUserId(Long userId);
}



