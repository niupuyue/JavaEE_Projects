package com.paulniu.service.impl;

import com.paulniu.dao.IUserDao;
import com.paulniu.dao.impl.UserDaoImpl;
import com.paulniu.domain.User;
import com.paulniu.service.IUserService;

public class UserServiceImpl implements IUserService {

    private IUserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        if (userDao.find(user.getUserName()) != null) {
            // 用户名已经存在，抛出异常 TODO
        }
        userDao.add(user);
    }

    @Override
    public User loginUser(String userName, String userPwd) {
        return userDao.find(userName, userPwd);
    }
}
