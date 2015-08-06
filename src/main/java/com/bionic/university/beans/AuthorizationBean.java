package com.bionic.university.beans;

import com.bionic.university.entity.User;
import com.bionic.university.services.UserService;

import javax.faces.bean.ManagedBean;
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
        StringBuilder resultLink = new StringBuilder();

        String roleName = getCurrentUser().getRole().getName();

        //        if (roleName.equals("admin") && success) {
//            return "admin";
//        } else if (roleName.equals("mentor") && success) {
//            return "mentor";
//        } else if (roleName.equals("student") && success) {
//            return "student";
//        } else {
//            return "index.html";
//        }

        if (roleName.equals("admin") && success) {
            resultLink.append("adminProfile"); // doesn't exist yet
        } else if (roleName.equals("mentor") && success) {
            resultLink.append("mentorProfile");
        } else if (roleName.equals("student") && success) {
            resultLink.append("userProfile");
        } else {
            resultLink.append("index.html");
        }

        resultLink.append("?faces-redirect=true&email=").append(email);

        return resultLink.toString();
    }

    public User getCurrentUser() {
//        ExternalContext context = FacesContext.getCurrentInstance()
//                .getExternalContext();
//        String email = context.getUserPrincipal().getName();
        return userService.findUserByEmail(email);
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
