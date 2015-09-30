package me.thomas.security;

import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by zhaoxs on 2015/9/29 0029.
 */
public class ServiceImpl {

    protected SqlSessionFactory sqlSessionFactory;

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
