package me.thomas.security.service.impl;

import me.thomas.security.ServiceImpl;
import me.thomas.security.def.PrincipalType;
import me.thomas.security.model.Permission;
import me.thomas.security.persistence.PermissionMapper;
import me.thomas.security.service.IPermissionService;
import me.thomas.security.service.IRoleService;
import me.thomas.security.service.IUserService;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaoxs on 2015/9/25 0025.
 */
public class PermissionService extends ServiceImpl implements IPermissionService {

    private final Logger logger = LoggerFactory.getLogger(PermissionService.class);

    private IUserService userService;
    private IRoleService roleService;

    public void grantPermission(PrincipalType principalType, String principalId, String resource, String[] permissionActions) {

        int actions = 0;
        for (String action : permissionActions) {
            int actionValue = this.getActionValue(resource, action);
            if (actionValue > 0) {
                actions |= actionValue;
            }
        }

        if (PrincipalType.USER.equals(principalType)) {
            userService.mergeResourceActions(principalId, resource, actions);
        } else if (PrincipalType.ROLE.equals(principalType)) {
            roleService.mergeResourceActions(principalId, resource, actions);
        }
    }

    public void revokePermission(PrincipalType principalType, String principalId, String resource, String permissionAction) {

        int actionValue = this.getActionValue(resource, permissionAction);

        if (actionValue > 0) {
            int actions = 0;
            if (PrincipalType.USER.equals(principalType)) {
                actions = userService.getResourceActions(principalId, resource) & ~actionValue;
                userService.mergeResourceActions(principalId, resource, actions);
            } else if (PrincipalType.ROLE.equals(principalType)) {
                actions = roleService.getResourceActions(principalId, resource) & ~actionValue;
                roleService.mergeResourceActions(principalId, resource, actions);
            }
        }

    }

    public boolean hasPermission(PrincipalType principalType, String principalId, String resource, String permissionAction) {

        int actionValue = this.getActionValue(resource, permissionAction);

        // 如果数据库中没有配置该操作对应的权限值，则默认谁都具有该操作的权限。
        if (actionValue == -1) {
            return true;
        }

        int actions = 0;
        if (PrincipalType.USER.equals(principalType)) {
            actions = userService.getResourceActions(principalId, resource);
        } else if (PrincipalType.ROLE.equals(principalType)) {
            actions = roleService.getResourceActions(principalId, resource);
        }

        return (actions & actionValue) > 0;
    }

    private int getActionValue(String resource, String action) {
        Permission permission = new Permission();
        permission.setResource(resource);
        permission.setAction(action);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            PermissionMapper permissionMapper = session.getMapper(PermissionMapper.class);
            return permissionMapper.getActionValue(permission);
        } catch (BindingException e) {
            return -1;
        } finally {
            session.close();
        }

    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }
}
