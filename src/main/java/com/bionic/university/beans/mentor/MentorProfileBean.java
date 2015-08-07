package com.bionic.university.beans.mentor;

import com.bionic.university.entity.Test;
import com.bionic.university.services.TestService;
import com.bionic.university.services.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Olexandr on 8/3/2015.
 */
@SessionScoped
@ManagedBean
public class MentorProfileBean {
    private List<Test> tests;


    @Inject
    UserService userService;
    @Inject
    TestService testService;

    private String email = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");


    public String getName() {
        return userService.findUserByEmail(email).getFirstName()+" "+userService.findUserByEmail(email).getLastName();
    }

    public String editTest(int testId) {
        return "editTest?testId" + testId;
    }

    public String viewResults(int testId) {
        return "results?testId" + testId;
    }

    public String viewUsers() {
        return "allUsers.xhtml";
    }

    public List<Test> getTests()
    {
        return testService.sortByCategory(tests);
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }


}
