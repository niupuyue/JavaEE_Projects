package com.paulniu.mybatis_01;

import com.paulniu.domain.QueryVo;
import com.paulniu.domain.User;

import java.util.List;

public interface IUserDao {

    List<User> findAll();

    User findById(Integer id);

    int saveUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);

    int findTotal();

    //根据QueryVo条件查询
    List<User> findByVo(QueryVo queryVo);
}
