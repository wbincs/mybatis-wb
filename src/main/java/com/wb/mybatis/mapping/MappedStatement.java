package com.wb.mybatis.mapping;

import com.wb.mybatis.session.Configuration;

/**
 * @Author wubin
 * @Date 2021/4/2 20:04
 * @Version 1.0
 * 对应的是Mapper.xml对应的配置，在这里只是配置了几个基本的，目前未包括全部。
 */
public class MappedStatement {

    private Configuration configuration;

    // mapper.xml文件所在的地址
    // 例如mappers/UserMapper.xml
    private String resource;

    //由二位坐标构成的ID=namespace.id
    // 例如com.wb.demo.mapper.UserMapper.getById
    private String id;

    //sql语句
    // 例如，select * from user_info where id = ?
    private String sql;

    //返回类型
    // 例如com.wb.demo.pojo.User
    private String resultType;

    // sql语句的类型
    // 例如SELECT
    private SqlCommandType sqlCommandType;

    public MappedStatement(Configuration configuration, String resource) {
        this.configuration = configuration;
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    @Override
    public String toString() {
        return "MappedStatement{" +
                "resource='" + resource + '\'' +
                ", configuration=" + configuration +
                ", id='" + id + '\'' +
                ", sql='" + sql + '\'' +
                ", resultType='" + resultType + '\'' +
                ", sqlCommandType=" + sqlCommandType +
                '}';
    }
}
