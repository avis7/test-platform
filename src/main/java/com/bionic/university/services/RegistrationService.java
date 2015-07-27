package com.bionic.university.services;

import com.bionic.university.dao.RoleDAO;
import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.Role;
import com.bionic.university.entity.User;
import org.hibernate.exception.ConstraintViolationException;

import javax.inject.Inject;
import java.util.Date;


public class RegistrationService {

    @Inject
    UserDAO userDAO;
    @Inject
    RoleDAO roleDAO;

    public String add(String firstName, String lastName, String email, String password, Date birthday, String phone){
        try {
            User user = new User(firstName, lastName, email, password, birthday, phone);
            user.setRole(roleDAO.find(1));
            userDAO.save(user);
            return "success";
        }catch (ConstraintViolationException e){
            return e.getConstraintName();
        }
        catch (Exception e){
            return e.getMessage();
        }

    }
}
