package me.thomas.security.persistence;

import me.thomas.security.model.Permission;

/**
 * Created by Thomas on 2015/9/27.
 */
public interface PermissionMapper {

    int getActionValue(Permission permission);

}
