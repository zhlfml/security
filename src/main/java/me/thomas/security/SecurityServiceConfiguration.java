package me.thomas.security;

import me.thomas.security.service.IPermissionService;
import me.thomas.security.persistence.RoleMapper;
import me.thomas.security.service.IRoleService;
import me.thomas.security.service.IUserService;
import me.thomas.security.service.impl.PermissionService;
import me.thomas.security.service.impl.RoleService;
import me.thomas.security.service.impl.UserService;
import me.thomas.security.persistence.PermissionMapper;
import me.thomas.security.persistence.UserMapper;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;

import javax.sql.DataSource;
import java.lang.*;

/**
 * Created by zhaoxs on 2015/9/28 0028.
 */
public class SecurityServiceConfiguration implements SecurityServices {

    protected IUserService userService = new UserService();
    protected IRoleService roleService = new RoleService();
    protected IPermissionService permissionService = new PermissionService();

    protected DataSource dataSource;
    protected SqlSessionFactory sqlSessionFactory;

    public SecurityServices buildSecurityServices() {
        init();
        return new SecurityServicesImpl(this);
    }

    protected void init() {
        initDataSource();
        initSqlSessionFactory();
        initServices();
    }

    protected void initDataSource() {
        if (dataSource == null) {
            throw new SecurityException("DataSource have to be specified in configuration.");
        }
    }

    protected void initSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            Environment environment = new Environment("default", new ManagedTransactionFactory(), dataSource);
            Configuration configuration = new Configuration(environment);
            configuration.getTypeAliasRegistry().registerAliases("me.thomas.security.model");
            configuration.addMapper(UserMapper.class);
            configuration.addMapper(RoleMapper.class);
            configuration.addMapper(PermissionMapper.class);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        }
    }

    protected void initServices() {
        initService(userService);
        initService(roleService);
        initService(permissionService);

        ((PermissionService) permissionService).setUserService(userService);
        ((PermissionService) permissionService).setRoleService(roleService);
    }

    protected void initService(Object service) {
        if (service instanceof ServiceImpl) {
            ((ServiceImpl) service).setSqlSessionFactory(sqlSessionFactory);
        }
    }

    public IPermissionService getPermissionService() {
        return permissionService;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
