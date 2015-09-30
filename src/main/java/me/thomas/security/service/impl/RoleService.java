package me.thomas.security.service.impl;

import me.thomas.security.ServiceImpl;
import me.thomas.security.model.RolePermission;
import me.thomas.security.persistence.RoleMapper;
import me.thomas.security.service.IRoleService;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaoxs on 2015/9/25 0025.
 */
public class RoleService extends ServiceImpl implements IRoleService {

    private final Logger logger = LoggerFactory.getLogger(RoleService.class);

    public int getResourceActions(String roleId, String resource) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setResource(resource);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            RoleMapper roleMapper = session.getMapper(RoleMapper.class);
            return roleMapper.getResourceActions(rolePermission);
        } catch (BindingException e) {
            return 0;
        } finally {
            session.close();
        }
    }

    public void mergeResourceActions(String roleId, String resource, int actions) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setResource(resource);
        rolePermission.setActions(actions);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            RoleMapper roleMapper = session.getMapper(RoleMapper.class);
            int result = roleMapper.updateResourceActions(rolePermission);
            if (result == 0) {
                roleMapper.saveResourceActions(rolePermission);
            }
        } finally {
            session.close();
        }
    }
}
