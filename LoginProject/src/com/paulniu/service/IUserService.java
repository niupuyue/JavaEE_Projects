package com.paulniu.service;

import com.paulniu.domain.User;

public interface IUserService {

    /**
     * 提供注册服务
     */
    int registerUser(User user);

    /**
     * 提供登录服务
     */
    User loginUser(String userName,String userPwd);

}
