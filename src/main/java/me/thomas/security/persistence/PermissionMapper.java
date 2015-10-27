package me.thomas.security.persistence;

import me.thomas.security.model.Permission;

import java.util.List;

/**
 * Created by Thomas on 2015/9/27.
 */
public interface PermissionMapper {

    Integer getActionValue(Permission permission);

    List<String> findAllResources();

    List<Permission> findPermissionsByResource(String resource);

    int updateActionValue(Permission permission);

}
