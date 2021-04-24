package com.wb.mybatis.binding;

import com.wb.mybatis.session.Configuration;
import com.wb.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wubin
 * @Date 2021/4/4 0:26
 * @Version 1.0
 */
public class MapperRegistry {

    private final Configuration configuration;
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 注册代理工厂
     */
    public <T> void addMapper(Class<T> type) {
        this.knownMappers.put(type, new MapperProxyFactory<>(type));
    }

    /**
     * 获取代理工厂实例
     */
    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>)this.knownMappers.get(type);
        return mapperProxyFactory.newInstance(sqlSession);
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
