package com.xiao.weather.dao.role;

import com.xiao.weather.common.vo.role.RoleVo;
import com.xiao.weather.dao.core.BaseDao;
import com.xiao.weather.entity.role.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiao_elevener
 */
@Repository
public class RoleDaoImpl extends BaseDao<Role> implements RoleDao {

    private final String FIND_ROLE_BY_USER_ID="findRolesByUserId";

    @Override
    public List<RoleVo> findRolesByUserId(Long userId) {
        return sqlSession.selectList(getStatementPrefix()+FIND_ROLE_BY_USER_ID,userId);
    }
}


