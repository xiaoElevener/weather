package com.xiao.weather.dao.user;

import com.xiao.weather.common.vo.user.UserVo;
import com.xiao.weather.dao.core.BaseDao;
import com.xiao.weather.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiao_elevener
 */
@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    private final String LOCK_USER = "lockUser";

    @Override
    public void lockUser(long id) {
        sqlSession.update(getStatementPrefix() + LOCK_USER, id);
    }

    private final String FIND_USER = "findUser";

    @Override
    public UserVo findUser(UserVo userVo) {
        return sqlSession.selectOne(getStatementPrefix() + FIND_USER, userVo);
    }

    private final String GET_LOGIN_NAME_LIST = "getLoginNameList";

    @Override
    public List<String> getLoginNameList() {
        return sqlSession.selectList(getStatementPrefix() + GET_LOGIN_NAME_LIST);
    }

    private final String UPDATE_LAST_ATTEMPTED_LOGIN_TIME = "updateLastAttemptedLoginTime";

    @Override
    public void updateLastAttemptedLoginTime(String loginName) {
        sqlSession.update(getStatementPrefix() + UPDATE_LAST_ATTEMPTED_LOGIN_TIME, loginName);
    }

    private final String FIND_USER_BY_OPEN_ID = "findUserByOpenId";

    @Override
    public User findUserByOpenId(String openId) {
        return sqlSession.selectOne(getStatementPrefix() + FIND_USER_BY_OPEN_ID, openId);
    }
}


