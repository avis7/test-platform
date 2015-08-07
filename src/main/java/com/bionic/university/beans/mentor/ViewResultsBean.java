package com.bionic.university.beans.mentor;

import com.bionic.university.entity.Result;
import com.bionic.university.services.ResultService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by SERYOJA
 */
@ManagedBean
@ViewScoped
public class ViewResultsBean implements Serializable {
    private List<Result> results;
    private String testId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("testId");
    private String testName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("testName");

    @Inject
    ResultService resultService;

    public List<Result> getResults() {
        int id = Integer.valueOf(testId);
        results = resultService.getResultByTestId(id);
        results = resultService.sortByChecked(resultService.getSubmitedResults());
        return results;

    }

    public String getTestName() {
        return testName;
    }


}

