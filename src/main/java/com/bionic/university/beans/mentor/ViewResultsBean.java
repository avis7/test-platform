package com.bionic.university.beans.mentor;

import com.bionic.university.entity.Result;
import com.bionic.university.services.QuestionService;
import com.bionic.university.services.ResultService;

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
public class ViewResultsBean implements Serializable {
    private List<Result> results;
    private String testId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("testId");
    private String testName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("testName");
    private int questionAmount;

    public int getQuestionAmount() {
        return questionService.getQuestionsByTestId(testId).size();
    }

    @Inject
    ResultService resultService;
    @Inject
    QuestionService questionService;

    public List<Result> getResults() {
        int id = Integer.valueOf(testId);
        results = resultService.getResultByTestId(id);
        results = resultService.getAllSubmitedCheckedResults();
        return results;

    }

    public String getTestName() {
        return testName;
    }


}
