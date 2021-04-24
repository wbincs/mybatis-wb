package com.wb.mybatis.executor;

import com.wb.mybatis.mapping.MappedStatement;

import java.util.List;

/**
 * @Author wubin
 * @Date 2021/4/2 20:13
 * @Version 1.0
 */
public interface Executor {
    <E> List<E> query(MappedStatement ms, Object parameter);
}
