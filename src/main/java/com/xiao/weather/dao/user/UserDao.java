package com.xiao.weather.dao.user;

import com.xiao.weather.common.vo.user.UserVo;
import com.xiao.weather.dao.core.Dao;
import com.xiao.weather.entity.user.User;

import java.util.List;

/**
 * @author xiao_elevener
 */
public interface UserDao extends Dao<User> {

    /**
     * 锁定用户
     *
     * @param id
     */
    void lockUser(long id);


    /**
     * 登陆查找用户(只通过loginName和password查询
     *
     * @param userVo
     * @return
     */
    UserVo findUser(UserVo userVo);

    /**
     * 获取所有用户的账号
     *
     * @return
     */
    List<String> getLoginNameList();

    /**
     * 更新该用户尝试登录次数
     *
     * @param loginName
     */
    void updateLastAttemptedLoginTime(String loginName);

    /**
     * 通过openId查询用户姓名
     *
     * @param openId
     * @return
     */
    User findUserByOpenId(String openId);
}



