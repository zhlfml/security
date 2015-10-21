package me.thomas.security.model;

import java.io.Serializable;

/**
 * Created by zhaoxs on 2015/9/25 0025.
 */
public class UserPermission implements Serializable {

    private static final long serialVersionUID = 5330599411919809665L;
    private String userId;
    private String resource;
    private int actions;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
