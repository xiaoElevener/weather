package com.xiao.weather.service.user;

import com.xiao.weather.common.so.user.UserSo;
import com.xiao.weather.common.vo.user.UserVo;

import java.util.List;

/**
 * @author xiao_elevener
 */
public interface UserService {

    /**
     * 创建用户
     *
     * @param userVo
     */
    void createUser(UserVo userVo);

    /**
     * 登录名唯一，删除用软删除
     *
     * @param id
     */
    void deleteUser(long id);

    /**
     * 通过登陆名,密码 进行登录
     *
     * @param userVo 前端只提供loginName,password
     * @return
     */
    UserVo login(UserVo userVo);

    /**
     * 条件查询用户列表
     *
     * @return
     */
    List<UserVo> findUserVosBySo(UserSo userSo);

    /**
     * 更新用户
     */
    void updateUser(UserVo userVo);

    /**
     * 根据条件查询数量
     *
     * @param userSo
     * @return
     */
    int countByUserSo(UserSo userSo);

    /**
     * 获取未锁定的用户登录名
     *
     * @return
     */
    List<String> getLoginNameList();

    /**
     * 绑定用户
     *
     * @param userVo
     * @return
     */
    Boolean bind(UserVo userVo);
}
