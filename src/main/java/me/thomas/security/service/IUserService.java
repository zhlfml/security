package me.thomas.security.service;

/**
 * Created by zhaoxs on 2015/9/25 0025.
 */
public interface IUserService {

    int getResourceActions(String userId, String resource);

    void mergeResourceActions(String userId, String resource, int actions);
}
