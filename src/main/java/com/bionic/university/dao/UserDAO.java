package com.bionic.university.dao;

import com.bionic.university.entity.User;
import com.bionic.university.interceptor.TxInterceptorBinding;

import javax.persistence.Query;


@TxInterceptorBinding
public class UserDAO extends AbstractDAO<User> {

    public UserDAO() {
        super(User.class);
    }

    public boolean exist(String email){
        return true;
    }

    public User findUserByEmail(String email) {
        Query query = em.createNamedQuery("getUserByEmail");
        query.setParameter("login", email);
        return (User) query.getSingleResult();
    }

}
