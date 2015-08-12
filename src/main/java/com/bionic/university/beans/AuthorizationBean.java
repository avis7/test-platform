package com.bionic.university.beans;

import com.bionic.university.entity.User;
import com.bionic.university.services.UserService;

import javax.faces.application.FacesMessage;
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
        User currentUser = getCurrentUser();
        StringBuilder resultLink = new StringBuilder();

        if (currentUser != null) {

            try {
                ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).
                        login(currentUser.getEmail(), currentUser.getPassword());
            } catch (ServletException e) {
                e.printStackTrace();
            }

            boolean success = userService.authorization(email, password);

            String roleName = currentUser.getRole().getName();

            if (roleName.equals("admin") && success) {
                resultLink.append("admin/adminProfile");
            } else if (roleName.equals("mentor") && success) {
                resultLink.append("mentor/mentorProfile");
            } else if (roleName.equals("student") && success) {
                resultLink.append("student/userProfile");
            }
            resultLink.append("?faces-redirect=true&email=").append(email);
        } else {
            FacesMessage message = new FacesMessage("Такого користувача не існує в нашій системі. " +
                    "Перевірте введені дані.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            resultLink.append("authorization?faces-redirect=true&success" + false);
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
