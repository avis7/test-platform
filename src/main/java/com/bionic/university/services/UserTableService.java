package com.bionic.university.services;

import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.User;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rondo104 on 29.07.2015.
 */
public class UserTableService {

    @Inject
    UserDAO userDAO;

    List<User> user =  new ArrayList<User>();


    public List<User> getUserFromDB(){
        user = userDAO.findAll();
        return user;
    }

}
