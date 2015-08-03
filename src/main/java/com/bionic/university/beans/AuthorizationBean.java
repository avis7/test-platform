package com.bionic.university.beans;

import com.bionic.university.entity.User;
import com.bionic.university.services.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@SessionScoped
@ManagedBean
public class AuthorizationBean {
    private String email;
    private String password;

    @Inject
     private UserService userService;

    public String authorization() {
        boolean success = userService.authorization(email, password);
        return success ? "userProfile?faces-redirect=true&email=" + email : "authorization?success=" + success;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
