package com.xiao.weather.service.user;

import com.sun.org.apache.regexp.internal.RE;
import com.xiao.weather.common.exception.BizException;
import com.xiao.weather.common.so.user.UserSo;
import com.xiao.weather.common.vo.user.UserVo;
import com.xiao.weather.dao.role.RoleDao;
import com.xiao.weather.dao.user.UserDao;
import com.xiao.weather.entity.user.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xiao.weather.service.core.AbstractServiceImpl;

import java.util.List;

/**
 * @author xiao_elevener
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends AbstractServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public void createUser(UserVo userVo) {
        checkUser(userVo);
        userDao.insert(dozer.convert(userVo, User.class));
    }

    /**
     * 检查用户数据
     *
     * @param userVo
     */
    private void checkUser(UserVo userVo) {
        beforeCheckUserHandle(userVo);
        checkLoginName(userVo.getLoginName());
    }


    /**
     * 检查登录名是否重复
     *
     * @param loginName
     */
    private void checkLoginName(String loginName) {
        int count = getCountByLoginName(loginName);
        if (count > 0) {
            throw new BizException("【创建用户异常】，该登陆名已被注册");
        }
    }


    private int getCountByLoginName(String loginName) {
        UserSo userSo = new UserSo();
        //保留被锁定账户的登陆名
        userSo.setLocked(null);
        userSo.setLoginName(loginName);
        return userDao.selectCountBySo(userSo);
    }

    /**
     * 对用户数据进行处理
     *
     * @param userVo
     */
    private void beforeCheckUserHandle(UserVo userVo) {

        if (userVo == null) {
            throw new BizException("【创建用户异常】，用户信息不完整");
        }

        if (StringUtils.isEmpty(userVo.getLoginName())) {
            throw new BizException("【创建用户异常】，登陆为空");
        }

        if (StringUtils.isEmpty(userVo.getUserName())) {
            throw new BizException("【创建用户异常】，用户名为空");
        }

        if (StringUtils.isEmpty(userVo.getPassword())) {
            throw new BizException("【创建用户异常】，密码为空");
        }

    }

    @Override
    public void deleteUser(String loginName) {
        int count = getCountByLoginName(loginName);
        if (count == 0) {
            throw new BizException("【删除用户异常】,该用户不存在");
        }
        userDao.lockUser(loginName);
    }

    @Override
    public void login(UserVo userVo) {
        //TODO
        User user = userDao.findUser(userVo);
    }

    @Override
    public List<UserVo> findUserVosBySo(UserSo userSo) {
        List<UserVo> userVoList = userDao.selectPaginationVoBySo(userSo);
        userVoList.stream().forEach(userVo -> userVo.setRoles(roleDao.findRolesByUserId(userVo.getId())));
        return  userVoList;
    }


    @Override
    public void updateUser(UserVo userVo) {
        userDao.update(dozer.convert(userVo, User.class));
    }

    @Override
    public int countByUserSo(UserSo userSo) {
        return userDao.selectCountBySo(userSo);
    }
}
