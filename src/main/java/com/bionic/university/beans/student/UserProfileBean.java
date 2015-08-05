package com.bionic.university.beans.student;

import com.bionic.university.entity.Result;
import com.bionic.university.entity.Test;
import com.bionic.university.services.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

/**
 * Created by Olexandr on 7/30/2015.
 */
@SessionScoped //!!!!!!!!!!!!!!!!!!!!!!!RequestScoped ??? !!!!!!!!!!!!!!!!!!!!
@ManagedBean
public class UserProfileBean {
    private Collection<Test> tests;
    private Collection<Result> results;

    @Inject
    UserService userService;


    private String email = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");





    public String getName() {
        return userService.findUserByEmail(email).getFirstName()+" "+userService.findUserByEmail(email).getLastName();
    }


    public String startTest(Test test) {
        return "testSasha?faces-redirect=true&testId=" + test.getId() + "&email=" + email;
    }


    public Collection<Test> getTests() {
        tests = userService.findUserByEmail(email).getTests();
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public Collection<Result> getResults() {
        results = userService.findUserByEmail(email).getResults();
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
