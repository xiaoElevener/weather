package com.xiao.weather.dao.user;

import com.xiao.weather.common.vo.user.UserVo;
import com.xiao.weather.dao.core.Dao;
import com.xiao.weather.entity.user.User;

/**
 * @author xiao_elevener
 */
public interface UserDao extends Dao<User> {

    /**
     * 锁定用户
     * @param loginName
     */
    void lockUser(String loginName);


    /**
     * 登陆查找用户(只通过loginName和password查询
     * @param userVo
     */
    User findUser(UserVo userVo);
}



