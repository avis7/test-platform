package com.bionic.university.beans;

import com.bionic.university.entity.User;
import com.bionic.university.services.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@SessionScoped
@ManagedBean
public class AuthorizationBean {

    private String email;
    private String password;

    @Inject
    private UserService userService;

    public String authorization() {
        try {
            ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).
                    login(getCurrentUser().getEmail(), getCurrentUser().getPassword());
        } catch (ServletException e) {
            e.printStackTrace();
        }

        boolean success = userService.authorization(email, password);
        StringBuilder resultLink = new StringBuilder();

        String roleName = getCurrentUser().getRole().getName();

        if (roleName != null) {
            if (roleName.equals("admin") && success) {
                resultLink.append("admin/adminProfile");
            } else if (roleName.equals("mentor") && success) {
                resultLink.append("mentor/mentorProfile");
            } else if (roleName.equals("student") && success) {
                resultLink.append("student/userProfile");
            }
            resultLink.append("?faces-redirect=true&email=").append(email);
        } else {
            resultLink.append("index.xhtml");
        }

        return resultLink.toString();
    }

    public User getCurrentUser() {
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
