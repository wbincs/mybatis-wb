package com.wb.demo.mapper;

import com.wb.demo.pojo.User;

import java.util.List;

/**
 * @Author wubin
 * @Date 2021/4/2 20:00
 * @Version 1.0
 */
public interface UserMapper {

    User getById(int id);

    List<User> getAll();
}
