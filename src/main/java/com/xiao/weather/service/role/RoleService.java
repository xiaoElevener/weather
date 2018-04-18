package com.xiao.weather.service.role;

import com.xiao.weather.common.vo.role.RoleVo;
import com.xiao.weather.entity.user.User;

import java.util.List;

/**
 * @author xiao_elevener
 */
public interface RoleService {

    /**
     * 通过用户id查询角色
     * @param userId
     * @return
     */
    List<RoleVo> findRolesByUserId(Long userId);

    /**
     * 获取所有角色
     * @return
     */
    List<RoleVo> findAllRoles();


    /**
     * 创建角色
     * @param roleVo
     */
    void createRole(RoleVo roleVo);


    /**
     * 更新角色
     * @param roleVo
     */
    void updateRole(RoleVo roleVo);

    /**
     * 删除角色
     * @param roleId
     */
    void deleteRole(long roleId);

}
