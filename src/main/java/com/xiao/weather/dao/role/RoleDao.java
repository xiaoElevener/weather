package com.xiao.weather.dao.role;

import java.util.List;
import com.xiao.weather.dao.core.Dao;
import com.xiao.weather.entity.role.Role;

/**
 * @author xiao_elevener
 */
public interface RoleDao extends Dao<Role> {
    /**
     * 通过用户id查询用户所属角色
     * @param id
     * @return
     */
    List<Role> findRolesByUserId(Long id);
}



