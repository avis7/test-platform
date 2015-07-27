package com.bionic.university.dao;

import com.bionic.university.entity.User;
import com.bionic.university.interceptor.TxInterceptorBinding;


@TxInterceptorBinding
public class UserDAO extends AbstractDAO<User> {


    public UserDAO() {
        super(User.class);
    }


}
