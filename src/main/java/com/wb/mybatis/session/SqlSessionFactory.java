package com.wb.mybatis.session;

/**
 * @Author wubin
 * @Date 2021/4/2 20:02
 * @Version 1.0
 * SqlSessionFactory主要是管理SqlSession。
 */
public interface SqlSessionFactory {
    public Configuration getConfiguration();
    public SqlSession openSession();
}
