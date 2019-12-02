package com.paulniu.mybatis_01;

import com.paulniu.domain.Account;
import com.paulniu.domain.AccountUser;

import java.util.List;

public interface IAccountUserDao {
    List<AccountUser> findAll();

    List<Account> findAll2();
}
