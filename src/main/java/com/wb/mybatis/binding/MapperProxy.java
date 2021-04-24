package com.wb.mybatis.binding;

import com.wb.mybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 * @Author wubin
 * @Date 2021/4/2 20:26
 * @Version 1.0
 */
public class MapperProxy <T> implements InvocationHandler, Serializable {

    private SqlSession sqlSession;
    // mapperInterface
    private final Class<T> mapperInterface;
    // method缓存
    private final Map<Method, MapperMethod> methodCache;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        final MapperMethod mapperMethod = cachedMapperMethod(method);
        return mapperMethod.execute(sqlSession, args);
        // 代理类proxy调用了某个方法method，传入的参数是args
        // 一般做法：
        // 获取method的参数、返回类型等，然后根据这些进行进一步的处理
        // 例如，有一个接口Hello，有一个抽象hello()方法，就可以判断method是否是hello()，如果是，则进行什么处理
//        String statement = method.getDeclaringClass().getName() + "." + method.getName();
//        //isAssignableFrom方法是判断是否为某个类的父类
//        if(Collection.class.isAssignableFrom(method.getReturnType())) {
//            return sqlSession.selectList(statement, null==args ? null:args[0]);
//        }else {
//            return sqlSession.selectOne(statement, null==args ? null:args[0]);
//        }
    }

    private MapperMethod cachedMapperMethod(Method method) {
        return methodCache.computeIfAbsent(method, k -> new MapperMethod(mapperInterface, method, sqlSession.getConfiguration()));
    }

}