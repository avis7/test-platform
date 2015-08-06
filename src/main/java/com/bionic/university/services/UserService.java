package com.bionic.university.services;

import com.bionic.university.dao.ResultDAO;
import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.Result;
import com.bionic.university.entity.Test;
import com.bionic.university.entity.User;

import javax.inject.Inject;


public class UserService {

    @Inject
    private UserDAO userDAO;
    private ResultDAO resultDAO;

    public boolean authorization(String email, String password) {
        User user = userDAO.findUserByEmail(email);
        if (user == null){
            return false;
        }
        return user.getPassword().equals(password);
    }

    public boolean submitTest(Test test, String email){
        try {
            int userId = userDAO.findUserByEmail(email).getId();
            Result result = resultDAO.findResultByUserIdAndTestId(userId, test.getId());
            if (result.isSubmited()){
                return false;
            }
            result.setSubmited(true);
            resultDAO.update(result);
            return true;
        }catch (Exception e){

        }
        return false;
    }

    public User findUserByEmail(String email){
        return userDAO.findUserByEmail(email);
    }








    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

}
