package me.thomas.security.service;

import me.thomas.security.def.PrincipalType;

/**
 * Created by zhaoxs on 2015/9/25 0025.
 */
public interface IPermissionService {

    void grantPermission(PrincipalType principalType, String principalId, String resource, String[] permissionActions);

    void revokePermission(PrincipalType principalType, String principalId, String resource, String permissionAction);

    boolean hasPermission(PrincipalType principalType, String principalId, String resource, String permissionAction);

}
