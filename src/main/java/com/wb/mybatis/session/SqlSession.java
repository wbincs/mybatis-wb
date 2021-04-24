package com.wb.mybatis.session;

import java.util.List;

/**
 * @Author wubin
 * @Date 2021/4/2 20:02
 * @Version 1.0
 * SqlSession主要是进行数据库操作的。
 */
public interface SqlSession {

    <T> T selectOne(String statement, Object parameter);

    <E> List<E> selectList(String statement);

    <E> List<E> selectList(String statement, Object parameter);

    <T> T getMapper(Class<T> type);

    Configuration getConfiguration();
}
