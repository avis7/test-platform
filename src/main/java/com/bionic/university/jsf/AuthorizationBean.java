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
public class AuthorizationBean {
    protected String email;
    private String password;

    @Inject
     private UserService userService;

    public String authorization() {
        boolean success = userService.authorization(email, password);
        return success ? "userProfile?email=" + email : "authorization?success=" + success;
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
