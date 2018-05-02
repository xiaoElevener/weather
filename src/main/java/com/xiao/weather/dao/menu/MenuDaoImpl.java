package com.xiao.weather.dao.menu;

import com.xiao.weather.dao.core.BaseDao;
import com.xiao.weather.entity.menu.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiao_elevener
 */
@Repository
public class MenuDaoImpl extends BaseDao<Menu> implements MenuDao {

    private final String FIND_PATHS = "findPaths";

    @Override
    public List<String> findPaths(List<Long> roleIds) {
        return sqlSession.selectList(getStatementPrefix() + FIND_PATHS, roleIds);
    }
}


