package com.bionic.university.beans.mentor;

import com.bionic.university.entity.Result;
import com.bionic.university.services.ResultService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by rondo104 on 04.08.2015.
 */
@RequestScoped
@ManagedBean
public class exportResultTestBean implements Serializable {
    private Collection<Result> results;

    private String testId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("testId");
    private String testName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("testName");

    @Inject
    ResultService resultService;

    public Collection<Result> getResults(){
        //TODO
        int id = Integer.valueOf(testId);
        results = resultService.getResultByTestId(id);
        return results;
    }

    public String getTestName(){
        return testName;
    }
}
