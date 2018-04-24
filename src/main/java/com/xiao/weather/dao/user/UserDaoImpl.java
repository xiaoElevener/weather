package com.xiao.weather.dao.user;

import com.xiao.weather.common.vo.user.UserVo;
import org.springframework.stereotype.Repository;
import com.xiao.weather.dao.core.BaseDao;
import com.xiao.weather.entity.user.User;

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
}


