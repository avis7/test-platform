package com.bionic.university.services;

import com.bionic.university.dao.RoleDAO;
import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.Role;
import com.bionic.university.entity.Test;
import com.bionic.university.entity.User;

import javax.inject.Inject;
import java.util.List;


public class UserService {

    @Inject
    UserDAO userDAO;
    @Inject
    RoleDAO roleDAO;

    public boolean authorization(String email, String password) {
        User user = userDAO.findUserByEmail(email);
        if (user == null){
            return false;
        }
        return user.getPassword().equals(password);
    }

    public User findUserByEmail(String email){
        return userDAO.findUserByEmail(email);
    }

    public List<User> getAllUsers(){
        return userDAO.findAll();
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
