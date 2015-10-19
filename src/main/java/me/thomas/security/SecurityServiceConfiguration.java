package me.thomas.security;

import me.thomas.security.persistence.PermissionMapper;
import me.thomas.security.persistence.RoleMapper;
import me.thomas.security.persistence.UserMapper;
import me.thomas.security.service.IPermissionService;
import me.thomas.security.service.IRoleService;
import me.thomas.security.service.IUserService;
import me.thomas.security.service.impl.PermissionService;
import me.thomas.security.service.impl.RoleService;
import me.thomas.security.service.impl.UserService;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;

import javax.sql.DataSource;

/**
 * Created by zhaoxs on 2015/9/28 0028.
 */
public class SecurityServiceConfiguration implements SecurityServices {

    protected IUserService userService = new UserService();
    protected IRoleService roleService = new RoleService();
    protected IPermissionService permissionService = new PermissionService();

    protected String jdbcUrl;
    protected String jdbcDriver;
    protected String jdbcUsername;
    protected String jdbcPassword;
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
            if (jdbcUrl == null || jdbcDriver == null || jdbcUsername == null || jdbcPassword == null) {
                throw new SecurityException("DataSource or JDBC properties have to be specified in a process engine configuration");
            }
            PooledDataSource pooledDataSource =
                    new PooledDataSource(this.getClass().getClassLoader(), jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);

            dataSource = pooledDataSource;
        }

        if (dataSource instanceof PooledDataSource) {
            // ACT-233: connection pool of Ibatis is not properely initialized if this is not called!
            ((PooledDataSource)dataSource).forceCloseAll();
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

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
