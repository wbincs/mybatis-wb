package com.wb.mybatis.binding;

import com.wb.mybatis.mapping.MappedStatement;
import com.wb.mybatis.session.Configuration;
import com.wb.mybatis.session.SqlSession;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Optional;

/**
 * @Author wubin
 * @Date 2021/4/24 0:12
 * @Version 1.0
 */
public class MapperMethod {

    private final Configuration configuration;
    // mapperInterface 和 method 可以组合成statementId，来获取MappedStatement
    // 实现的接口
    private final Class<?> mapperInterface;
    // 调用的方法
    private final Method method;

    public MapperMethod(Class<?> mapperInterface, Method method, Configuration configuration) {
        this.mapperInterface = mapperInterface;
        this.method = method;
        this.configuration = configuration;
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        //statementId为mapper类全名+方法名，用来获取MappedStatement
        String statementId = this.mapperInterface.getName() + "." + method.getName();
        MappedStatement ms = this.configuration.getMappedStatement(statementId);
        Object result = null;
        switch (ms.getSqlCommandType()) {
            case SELECT: {
                System.out.println("SELECT");
                Class<?> returnType = method.getReturnType();
                if (Collection.class.isAssignableFrom(returnType)) {
                    result =  sqlSession.selectList(statementId, null==args ? null:args[0]);
                } else {
                    result = sqlSession.selectOne(statementId, null==args ? null:args[0]);
                }
                break;
            }
            case UPDATE: {
//                sqlSession.update(statementId, args);
                System.out.println("UPDATE");
                break;
            }
            case INSERT: {
                System.out.println("INSERT");
                break;
            }
            case DELETE: {
                System.out.println("DELETE");
                break;
            }
            default: {
                //TODO 其他方法待实现
                break;
            }
        }
        return result;
    }

}
