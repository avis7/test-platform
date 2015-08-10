package com.bionic.university.beans.mentor;

import com.bionic.university.entity.Question;
import com.bionic.university.entity.Result;
import com.bionic.university.services.QuestionService;
import com.bionic.university.services.ResultService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by sergey on 8/3/2015.
 */
@ManagedBean
@ViewScoped
public class ViewCheckedResultsBean implements Serializable {
    private List<Result> results;
    private String testName;

    private String testId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("testId");


    @PostConstruct
    public void init() {
        List<com.bionic.university.entity.Result> results;
        results = resultService.getCheckedResultsByTestId(testId);
        if (results != null) {
            testName = results.get(0).getTest().getTestName();

        }

    }


    @Inject
    ResultService resultService;
    @Inject
    QuestionService questionService;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public class Result {
        private int userMark = 0;
        private int fullMArk = 0;


        public int getUserMark() {
            return userMark;
        }

        public void setUserMark(int userMark) {
            this.userMark = userMark;
        }

        public int getFullMArk() {
            return fullMArk;
        }

        public void setFullMArk(int fullMArk) {
            this.fullMArk = fullMArk;
        }
    }
}
