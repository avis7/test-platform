package com.bionic.university.services;

import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.User;

import javax.inject.Inject;


public class UserService {

    @Inject
    UserDAO userDAO;
    @Inject
    TestService testService;

    public boolean authorization(String email, String password) {
        User user = userDAO.findUserByEmail(email);
        if (user == null){
            return false;
        }
        return user.getPassword().equals(password);
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }


}
