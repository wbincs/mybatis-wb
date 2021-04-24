package com.wb.demo;

import com.wb.demo.mapper.UserMapper;
import com.wb.demo.pojo.User;
import com.wb.mybatis.session.SqlSession;
import com.wb.mybatis.session.SqlSessionFactory;
import com.wb.mybatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @Author wubin
 * @Date 2021/4/2 20:08
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        System.out.println(sqlSessionFactory);
//        System.out.println(sqlSessionFactory.getConfiguration().getJDBCProperties().getUrl());
        System.out.println("------------------Mybatis准备工作完成，配置解析完成--------------------------");

        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = null;
        List<User> users = null;

//        //使用sqlSession直接查询
//        user = sqlSession.selectOne("com.wb.demo.mapper.UserMapper.getById",1);
//        users = sqlSession.selectList("com.wb.demo.mapper.UserMapper.getAll");

        //使用Mapper
        UserMapper demoMapper = sqlSession.getMapper(UserMapper.class);
        user = demoMapper.getById(1);
        users = demoMapper.getAll();

        System.out.println(user);
        System.out.println(users);
    }

}
