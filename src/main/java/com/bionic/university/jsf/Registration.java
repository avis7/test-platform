package com.bionic.university.jsf;

import com.bionic.university.dao.RoleDAO;
import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.Date;

/**
 * Created by c2612 on 23.07.2015.
 */
@SessionScoped
@ManagedBean
public class Registration {


    private String lastName;
    private String name;
    private String birthday;
    private String phone;
    private String email;

    @Inject
    UserDAO udao;
    @Inject
    RoleDAO roleDAO;


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void doReg(){
        System.out.println("sss");
        User user =  new User("aa",
                "aa",
                "aa@",
                "bb",
                new Date(),
                "1213"
        );

        user.setRole(roleDAO.find(1));
        udao.save(user);
        System.out.println(udao.findAll());

    }

//    public UserDAO getUserDAO() {
//        return userDAO;
//    }
//
//    public void setUserDAO(UserDAO userDAO) {
//        this.userDAO = userDAO;
//    }

}
