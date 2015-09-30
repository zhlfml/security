package me.thomas.security.persistence;

import me.thomas.security.model.RolePermission;

/**
 * Created by Thomas on 2015/9/27.
 */
public interface RoleMapper {

    int getResourceActions(RolePermission rolePermission);

    int saveResourceActions(RolePermission rolePermission);

    int updateResourceActions(RolePermission rolePermission);

}
