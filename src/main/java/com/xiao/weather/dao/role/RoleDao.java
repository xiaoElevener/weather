package com.xiao.weather.dao.role;

import com.xiao.weather.common.vo.role.RoleVo;
import com.xiao.weather.dao.core.Dao;
import com.xiao.weather.entity.role.Role;

import java.util.List;

/**
 * @author xiao_elevener
 */
public interface RoleDao extends Dao<Role> {
    /**
     * 通过用户id查询用户所属角色
     * @param id
     * @return
     */
    List<RoleVo> findRolesByUserId(Long id);
}



