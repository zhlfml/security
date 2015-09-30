package me.thomas.security;

import me.thomas.security.service.IPermissionService;

/**
 * Created by zhaoxs on 2015/9/29 0029.
 */
public class SecurityServicesImpl implements SecurityServices {

    protected IPermissionService permissionService;

    public SecurityServicesImpl(SecurityServiceConfiguration configuration) {
        permissionService = configuration.getPermissionService();
    }

    public IPermissionService getPermissionService() {
        return this.permissionService;
    }
}
