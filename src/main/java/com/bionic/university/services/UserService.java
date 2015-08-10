package com.bionic.university.services;

import com.bionic.university.dao.ResultDAO;
import com.bionic.university.dao.RoleDAO;
import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.Result;
import com.bionic.university.entity.Test;
import com.bionic.university.entity.Role;
import com.bionic.university.entity.Test;
import com.bionic.university.entity.User;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.List;


public class UserService {

    
    @Inject
    private RoleDAO roleDAO;
    @Inject
    private UserDAO userDAO;
    @Inject
    private ResultDAO resultDAO;

    public boolean authorization(String email, String password) {
        try {
            User user = userDAO.findUserByEmail(email);
            return user.getPassword().equals(password);
        }catch (IllegalArgumentException e){
            System.out.println("dfce");

        }catch (Exception e0){
            System.out.println("frd");
        }
        return false;

    }

    public boolean submitTest(Test test, String email) {
        Date beginTime = new Date();
        try {
            int userId = userDAO.findUserByEmail(email).getId();
            int testId = test.getId();
            Result result = resultDAO.findResultByUserIdAndTestId(userId, testId);
            if (result.isSubmited()) {
                return false;
            }
            result.setSubmited(true);
            result.setBeginTime(beginTime);
            resultDAO.update(result);
            return true;
        } catch (Exception e) {
            System.out.println("rfgb");
        }
        return false;
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }
    public User findUserByEmail(String email) {
        try {
            return userDAO.findUserByEmail(email);
        } catch (Exception e) {

        }
        return null;
    }

    public List<Test> getUserTests(String email) {
        ArrayList<Test> availableTests = new ArrayList<Test>();
        try {
            User user = userDAO.findUserByEmail(email);
            for (Result result : user.getResults()) {
                if (!result.isSubmited() && !result.getTest().isArchived() && result.getTest().getDeadline().before(new Date())) {
                    availableTests.add(result.getTest());
                }
            }
            return availableTests;
        } catch (Exception e) {

        }
        return null;
    }

    public List<Result> getUserResults(String email){
        ArrayList<Result> results = new ArrayList<Result>();
        try{
            User user = userDAO.findUserByEmail(email);
            for (Result result : user.getResults()) {
                if (result.isSubmited() && result.isChecked()) {
                    results.add(result);
                }
            }
            return results;
        } catch (Exception e) {

        }
        return null;
    }


    public UserDAO getUserDAO() {
        return userDAO;
    }

    public boolean editUserRole(User user, String newRoleValue) {
        try {
            user.setRole(roleDAO.findRoleByRoleName(newRoleValue));
            userDAO.update(user);
            return true;
        }catch (Exception e){}
        return false;
    }

//    public boolean editUserTest(User user, Test test){
//        try {
//
//        }catch (Exception e){
//
//        }
//    }

}
