package com.bionic.university.beans.student;

import com.bionic.university.entity.Result;
import com.bionic.university.entity.Test;
import com.bionic.university.entity.User;
import com.bionic.university.services.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@SessionScoped
@ManagedBean
public class UserProfileBean {
    private List<Test> tests;
    private List<Result> results;


    @Inject
    UserService userService;

    private String email = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");


    public String getName() {
        return userService.findUserByEmail(email).getFirstName() + " " + userService.findUserByEmail(email).getLastName();
    }


    public String startTest(Test test) {
        boolean success = userService.submitTest(test, email);
        return success ? "/test?faces-redirect=true&testId=" + test.getId() + "&email=" + email : "error.xhtml";
    }


    public List<Test> getTests() {
        tests = userService.getUserTests(email);
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public List<Result> getResults() {
        results = userService.getUserResults(email);
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
