package me.thomas.security.service;

import me.thomas.security.def.PrincipalType;
import me.thomas.security.model.Permission;

import java.util.List;

/**
 * Created by zhaoxs on 2015/9/25 0025.
 */
public interface IPermissionService {

    void grantPermission(PrincipalType principalType, String principalId, String resource, String[] permissionActions);

    void revokePermission(PrincipalType principalType, String principalId, String resource, String permissionAction);

    boolean hasPermission(PrincipalType principalType, String principalId, String resource, String permissionAction);

    List<String> findAllResources();

    List<Permission> findPermissionsByResource(String resource);

    /**
     * 将表中所有的resource所属的action重新生成合乎逻辑的value
     */
    void arrangeActionValue();

    /**
     * 获得角色或用户对应资源的权限值
     *
     * @param principalType 身份
     * @param principalId 身份ID
     * @param resource 资源
     * @return 权限值
     */
    int getResourceActions(PrincipalType principalType, String principalId, String resource);

}
