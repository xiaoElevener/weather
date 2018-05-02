package com.xiao.weather.dao.menu;

import com.xiao.weather.dao.core.Dao;
import com.xiao.weather.entity.menu.Menu;

import java.util.List;

/**
 * @author xiao_elevener
 */
public interface MenuDao extends Dao<Menu> {
    List<String> findPaths(List<Long> roleIds);
}



