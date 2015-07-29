package com.bionic.university.services;

import com.bionic.university.dao.RoleDAO;
import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.Role;
import com.bionic.university.entity.User;
import org.hibernate.exception.ConstraintViolationException;

import javax.inject.Inject;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Date;


public class UserService {

    @Inject
    UserDAO userDAO;
    @Inject
    RoleDAO roleDAO;

    public String add(String firstName, String lastName, String email, String password, Date birthday, String phone){
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            if(firstName.matches("^[\\w]{4,15}$") && lastName.matches("^[\\w]{4,15}$") &&
                    password.matches("^[\\w]{4,15}$") && phone.matches("^[\\d]{4,15}$")) {
                User user = new User(firstName, lastName, email, password, birthday, phone);
                user.setRole(roleDAO.find(1));
                userDAO.save(user);
                return "success";
            }
            return "Invalid input";
        }catch (ConstraintViolationException e){
            return "Duplicate of KEY fields";
        }catch (AddressException ex) {
            return "Invalid email input";
        }
        catch (Exception e){
            return "Whole ex"+e.getMessage();
        }
    }
}
