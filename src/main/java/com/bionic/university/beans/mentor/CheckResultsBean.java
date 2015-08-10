package com.bionic.university.beans.mentor;

import com.bionic.university.dao.QuestionDAO;
import com.bionic.university.dao.ResultDAO;
import com.bionic.university.entity.Question;
import com.bionic.university.entity.Result;
import com.bionic.university.services.QuestionService;
import com.bionic.university.services.ResultService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Olexandr on 8/3/2015.
 */
@ViewScoped
@ManagedBean
public class CheckResultsBean {
    private List<Result> resultsToCheck;

    @Inject
    private ResultService resultService;

    private String testId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("testId");

    @PostConstruct
    public void init(){
        resultsToCheck = resultService.getResultsToCheckByTestId(testId);
    }

    public String check(int resultId){
        return "checkResult?faces-redirect=true&resultId=" + resultId;
    }

    public List<Result> getResultsToCheck() {
        return resultsToCheck;
    }

    public void setResultsToCheck(List<Result> resultsToCheck) {
        this.resultsToCheck = resultsToCheck;
    }
}
