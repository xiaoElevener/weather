package com.xiao.weather.service.user;

import com.xiao.weather.common.vo.user.UserVo;

/**
 * @author xiao_elevener
 */
public interface UserService {

    /**
     * 创建用户
     * @param userVo
     */
    void createUser(UserVo userVo);

    /**
     * 登录名唯一，删除用软删除
     * @param loginName
     */
    void deleteUser(String loginName);

    /**
     * 通过登陆名,密码 进行登录
     * @param userVo 前端只提供loginName,password
     * @return
     */
    void login(UserVo userVo);


}