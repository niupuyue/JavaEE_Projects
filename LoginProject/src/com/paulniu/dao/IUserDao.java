package com.paulniu.dao;

import com.paulniu.domain.User;

public interface IUserDao {

    /**
     * 根据用户名和密码来查询用户
     */
    User find(String userName, String userPwd);

    /**
     * 添加用户
     */
    int add(User user);

    /**
     * 根据用户名查找用户
     */
    User find(String userName);

}
