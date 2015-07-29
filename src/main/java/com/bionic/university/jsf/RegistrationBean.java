package com.bionic.university.jsf;

import com.bionic.university.entity.Test;
import com.bionic.university.services.UserService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.Collection;
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
    private String response;
    private Collection<Test> tests;

    @Inject
    UserService userService;

    public String getResponse() {
        return response;
    }

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


    public String userAdd() {
        response = userService.add(firstName, lastName, email, password, birthday, phone);
        if (response.equals("success"))
            return "successful.xhtml?faces-redirect=true";
        return "unsuccessful.xhtml?faces-redirect=true";
    }

    public Collection<Test> getTests() {
        return tests;
    }

    public void setTests(Collection<Test> tests) {
        this.tests = tests;
    }


}