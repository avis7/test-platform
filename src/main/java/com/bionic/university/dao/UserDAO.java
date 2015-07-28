package com.bionic.university.dao;

import com.bionic.university.entity.Test;
import com.bionic.university.entity.User;
import com.bionic.university.interceptor.TxInterceptorBinding;

import java.util.ArrayList;
import java.util.Collection;


@TxInterceptorBinding
public class UserDAO extends AbstractDAO<User> {


    public UserDAO() {
        super(User.class);
    }

    public Collection<User> findUsersByTest(Test test){
        //TODO
        return null;
    }
}
