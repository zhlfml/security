package me.thomas.security.persistence;

import me.thomas.security.model.UserPermission;

/**
 * Created by Thomas on 2015/9/27.
 */
public interface UserMapper {

    Integer getResourceActions(UserPermission userPermission);

    int saveResourceActions(UserPermission userPermission);

    int updateResourceActions(UserPermission userPermission);

}
