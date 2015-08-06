package com.bionic.university.beans.mentor;

import com.bionic.university.Test;

import java.util.List;

/**
 * Created by Olexandr on 8/3/2015.
 */
public class MentorProfileBean {
    private List<Test> tests;

    public String editTest(int testId){
        return "editTest?testId" + testId;
    }

    public String viewResults(int testId){
        return "results?testId" + testId;
    }

    public String viewUsers(){
        return "allUsers.xhtml";
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }




}
