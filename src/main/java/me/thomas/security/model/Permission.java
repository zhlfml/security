package me.thomas.security.model;

import java.io.Serializable;

/**
 * Created by zhaoxs on 2015/9/25 0025.
 */
public class Permission implements Serializable {

    private static final long serialVersionUID = 1864347167414710533L;
    private String resource;
    private String action;
    private int value;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
