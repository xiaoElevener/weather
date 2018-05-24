package com.xiao.weather.service.user;

import com.xiao.weather.common.exception.BizException;
import com.xiao.weather.common.security.SecurityContext;
import com.xiao.weather.common.security.SecurityContextHolder;
import com.xiao.weather.common.so.user.UserSo;
import com.xiao.weather.common.vo.role.RoleVo;
import com.xiao.weather.common.vo.user.UserVo;
import com.xiao.weather.dao.account.AccountDao;
import com.xiao.weather.dao.menu.MenuDao;
import com.xiao.weather.dao.role.RoleDao;
import com.xiao.weather.dao.user.UserDao;
import com.xiao.weather.entity.account.Account;
import com.xiao.weather.entity.user.User;
import com.xiao.weather.service.core.AbstractServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private MenuDao menuDao;


    @Override
    public void createUser(UserVo userVo) {
        checkUser(userVo);
        userVo.setPasswordErrorTimes(0);
        Long userId = userDao.insert(dozer.convert(userVo, User.class));
        createAccount(userId);
    }

    /**
     * 创建账户
     *
     * @param userId
     */
    public void createAccount(Long userId) {
        Account account = new Account();
        account.setUserId(userId);
        account.setBalance(0D);
        account.setOverdraft(0);
        accountDao.insert(account);
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

    /**
     * 获取条件查询用户数
     *
     * @param loginName
     * @return
     */
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
    public void deleteUser(long id) {
        userDao.lockUser(id);
    }

    @Override
    public UserVo login(UserVo userVo) {
        userVo.setLockVersion(null);
        UserVo user = userDao.findUser(userVo);
        setPaths(user);
        updateLastAttemptedLoginTime(userVo.getLoginName());
        updateContext(user);
        return user;

    }

    @Override
    public void logout() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        SecurityContextHolder.clearContext();
    }

    @Override
    public List<UserVo> findUserVosBySo(UserSo userSo) {
        List<UserVo> userVoList = userDao.selectPaginationVoBySo(userSo);
        userVoList.stream().forEach(userVo -> userVo.setRoles(roleDao.findRolesByUserId(userVo.getId())));
        return userVoList;
    }


    @Override
    public void updateUser(UserVo userVo) {
        userDao.update(dozer.convert(userVo, User.class));
    }

    @Override
    public int countByUserSo(UserSo userSo) {
        return userDao.selectCountBySo(userSo);
    }

    @Override
    public List<String> getLoginNameList() {
        return userDao.getLoginNameList();
    }

    @Override
    public Boolean bind(UserVo userVo) {
        String openId = userVo.getOpenId();
        if (openId == null) {
            throw new BizException("未能关联微信账号！");
        }
        userVo.setOpenId(null);
        UserVo vo = userDao.findUser(userVo);
        if (vo != null) {
            if (vo.getOpenId() != null) {
                throw new BizException("该账号已被关联！");
            }
            vo.setOpenId(openId);
            userDao.update(dozer.convert(vo, User.class));
            return true;
        }
        return false;
    }

    /**
     * 更新用户尝试登录时间
     *
     * @param loginName
     */
    private void updateLastAttemptedLoginTime(String loginName) {
        userDao.updateLastAttemptedLoginTime(loginName);
    }


    private void setPaths(UserVo userVo) {
        if (userVo == null) {
            return;
        }
        Set<String> set = new HashSet<>();
        List<RoleVo> roleVoList = roleDao.findRolesByUserId(userVo.getId());
        userVo.setRoles(roleVoList);
        if (roleVoList != null) {
            set.addAll(menuDao.findPaths(roleVoList.stream().map(roleVo -> roleVo.getId()).collect(Collectors.toList())));
        }
        userVo.setPaths(new ArrayList<>(set));

    }

    /**
     * 更新登录状态
     *
     * @param user
     */
    private void updateContext(UserVo user) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null) {
            securityContext = new SecurityContext();
            SecurityContextHolder.initContext(securityContext);
        }
        securityContext.setUser(dozer.convert(user, User.class));
    }
}
