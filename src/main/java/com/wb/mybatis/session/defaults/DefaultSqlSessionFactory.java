package com.wb.mybatis.session.defaults;

import com.wb.mybatis.session.Configuration;
import com.wb.mybatis.executor.SimpleExecutor;
import com.wb.mybatis.executor.Executor;
import com.wb.mybatis.session.SqlSession;
import com.wb.mybatis.session.SqlSessionFactory;

/**
 * @Author wubin
 * @Date 2021/4/2 20:05
 * @Version 1.0
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Executor executor = configuration.newExecutor();
        return new DefaultSqlSession(configuration, executor);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

}
