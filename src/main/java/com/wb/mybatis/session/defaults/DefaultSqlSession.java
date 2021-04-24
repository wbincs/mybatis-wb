package com.wb.mybatis.session.defaults;

import com.wb.mybatis.binding.MapperProxy;
import com.wb.mybatis.mapping.MappedStatement;
import com.wb.mybatis.session.Configuration;
import com.wb.mybatis.executor.Executor;
import com.wb.mybatis.session.SqlSession;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @Author wubin
 * @Date 2021/4/2 20:17
 * @Version 1.0
 */

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration= configuration;
        this.executor = executor;
    }

    public <T> T selectOne(String statement,Object parameter) {
        List<T> list = executor.query(configuration.getMappedStatement(statement), parameter);
        if(list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public <E> List<E> selectList(String statement) {
        return selectList(statement, null);
    }

    public <E> List<E> selectList(String statement, Object parameter) {
        MappedStatement ms = configuration.getMappedStatement(statement);
        return executor.query(ms, parameter);
    }

    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}