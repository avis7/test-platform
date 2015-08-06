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

        String roleName = getCurrentUser().getRole().getName();
        if (roleName.equals("admin") && success) {
            return "admin/adminpage?faces-redirec=true&email" + email;
        } else if (roleName.equals("mentor") && success) {
            return "mentor/mentorpage?faces-redirect=true&email" + email;
        } else if (roleName.equals("student") && success) {
            return "userProfile?faces-redirect=true&email=" + email;
        } else {
            return "index.html";
        }
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
