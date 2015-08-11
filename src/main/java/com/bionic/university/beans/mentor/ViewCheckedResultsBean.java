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

    @Inject
    private ResultService resultService;
    @Inject
    private QuestionService questionService;


    @PostConstruct
    public void init() {
        results = resultService.getCheckedResultsByTestId(testId);
        if (results != null && results.size() != 0) {
            testName = results.get(0).getTest().getTestName();
        }else testName = "";
    }

    public int getMaxMark(Result result){
        int mark = 0;
        List<Question> questions = questionService.getQuestionsByTestId(String.valueOf(result.getTest().getId()));
        for (Question question : questions){
            mark += question.getMark();
        }
        return mark;
    }

    public double getTime(Result result){
        long beginTime = result.getBeginTime().getTime();
        long passTime = result.getPassTime().getTime();
        double time = (double)(passTime - beginTime)/60000;
        return Math.rint(100.0 * time) / 100.0;
    }

    public String viewResult(int resultId){
        return "viewCheckedResult?faces-redirect=true&resultId=" + resultId;
    }


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
}
