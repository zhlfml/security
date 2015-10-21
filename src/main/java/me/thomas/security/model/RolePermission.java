package me.thomas.security.model;

import java.io.Serializable;

/**
 * Created by zhaoxs on 2015/9/25 0025.
 */
public class RolePermission implements Serializable {

    private static final long serialVersionUID = -30070500448528570L;
    private String roleId;
    private String resource;
    private int actions;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public int getActions() {
        return actions;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }
}
