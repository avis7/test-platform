package com.bionic.university.services;

import com.bionic.university.dao.RoleDAO;
import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.Test;
import com.bionic.university.entity.User;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Date;


public class UserService {

    @Inject
    UserDAO userDAO;
//    @Inject
//    RoleDAO roleDAO;
    @Inject
    ResultService resultService;
    @Inject
    RoleService roleService;
    @Inject
    TestService testService;

    public String addUser(String firstName, String lastName, String email, String password, Date birthday, String phone, Collection<Test> tests){
        try {
            User user = new User(firstName, lastName, email, password, birthday, phone);
            user.setRole(roleService.findRole(1));
            userDAO.save(user);
            resultService.addResults(user, tests);
            return "Successful.xhtml";
        } catch (Exception e){
            System.out.println("e = " + e);
        }
        return "registration_form.xhtml";
    }
}
