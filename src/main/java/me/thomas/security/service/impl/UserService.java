package me.thomas.security.service.impl;

import me.thomas.security.ServiceImpl;
import me.thomas.security.model.UserPermission;
import me.thomas.security.persistence.UserMapper;
import me.thomas.security.service.IUserService;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaoxs on 2015/9/25 0025.
 */
public class UserService extends ServiceImpl implements IUserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public int getResourceActions(String userId, String resource) {
        UserPermission userPermission = new UserPermission();
        userPermission.setUserId(userId);
        userPermission.setResource(resource);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            return userMapper.getResourceActions(userPermission);
        } catch (BindingException e) {
            return 0;
        } finally {
            session.close();
        }
    }

    public void mergeResourceActions(String userId, String resource, int actions) {
        UserPermission userPermission = new UserPermission();
        userPermission.setUserId(userId);
        userPermission.setResource(resource);
        userPermission.setActions(actions);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            int result = userMapper.updateResourceActions(userPermission);
            if (result == 0) {
                userMapper.saveResourceActions(userPermission);
            }
        } finally {
            session.close();
        }
    }
}
