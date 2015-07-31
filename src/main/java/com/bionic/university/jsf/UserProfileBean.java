package com.bionic.university.jsf;

import com.bionic.university.entity.Result;
import com.bionic.university.entity.Test;
import com.bionic.university.services.TestService;
import com.bionic.university.services.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

/**
 * Created by Olexandr on 7/30/2015.
 */
@SessionScoped
@ManagedBean
public class UserProfileBean {
    private Collection<Test> tests;
    private Collection<Result> results;

    @Inject
    UserService userService;

    String email = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");

    public String startTest(int testId){
        return "test?testId=" + testId;
    }


    public Collection<Test> getTests() {
        tests = userService.getUserDAO().findUserByEmail(email).getTests();
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public Collection<Result> getResults() {
        results = userService.getUserDAO().findUserByEmail(email).getResults();
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
