package com.bionic.university.jsf;

import com.bionic.university.dao.RoleDAO;
import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.User;
import com.bionic.university.services.RegistrationService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.Date;

@SessionScoped
@ManagedBean
public class RegistrationBean {


    private String lastName;
    private String firstName;
    private String password;
    private Date birthday;
    private String phone;
    private String email;

    @Inject
    RegistrationService registrationService;


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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

    public void userAdd(){
registrationService.add(firstName, lastName, email, password, birthday, phone);
    }


}
