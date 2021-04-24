package com.wb.mybatis.session;

import com.wb.mybatis.binding.MapperRegistry;
import com.wb.mybatis.executor.Executor;
import com.wb.mybatis.executor.SimpleExecutor;
import com.wb.mybatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wubin
 * @Date 2021/4/2 20:04
 * @Version 1.0
 * Configuration这里就是统一进行管理配置以及提供一些对配置基本的操作方法。
 */
public class Configuration {

    /** JDBC配置项 */
    private JDBCProperties JDBCProperties;

    /** mapper代理注册器 */
    private MapperRegistry mapperRegistry = new MapperRegistry(this);

    /** mapper文件的select/update语句的id和SQL语句属性 **/
    private Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public JDBCProperties getJDBCProperties() {
        return JDBCProperties;
    }

    public void setJDBCProperties(JDBCProperties JDBCProperties) {
        this.JDBCProperties = JDBCProperties;
    }

    public <T> void addMapper(Class<T> type) {
        this.mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return this.mapperRegistry.getMapper(type, sqlSession);
    }

    public Executor newExecutor() {
        return new SimpleExecutor(this);
    }

}
