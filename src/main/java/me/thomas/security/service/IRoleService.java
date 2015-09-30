package me.thomas.security.service;

/**
 * Created by zhaoxs on 2015/9/25 0025.
 */
public interface IRoleService {

    int getResourceActions(String roleId, String resource);

    void mergeResourceActions(String userId, String resource, int actions);
}
