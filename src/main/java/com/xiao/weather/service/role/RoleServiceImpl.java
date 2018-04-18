package com.xiao.weather.service.role;

import com.xiao.weather.common.so.role.RoleSo;
import com.xiao.weather.common.vo.role.RoleVo;
import com.xiao.weather.dao.role.RoleDao;
import com.xiao.weather.entity.role.Role;
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
public class RoleServiceImpl extends AbstractServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<RoleVo> findRolesByUserId(Long userId) {
        //TODO
        return null;
    }

    @Override
    public List<RoleVo> findAllRoles() {
        RoleSo roleSo = new RoleSo();
        return roleDao.selectVoBySo(roleSo);
    }

    @Override
    public void createRole(RoleVo roleVo) {
        roleDao.insert(dozer.convert(roleVo, Role.class));
    }

    @Override
    public void updateRole(RoleVo roleVo) {
        roleDao.update(dozer.convert(roleVo, Role.class));
    }

    @Override
    public void deleteRole(long roleId) {
        roleDao.delete(roleId);
    }
}
