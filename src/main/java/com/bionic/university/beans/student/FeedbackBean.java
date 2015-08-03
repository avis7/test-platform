package com.bionic.university.beans.student;

import com.bionic.university.services.ResultService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * Created by Olexandr on 8/1/2015.
 */
@RequestScoped    //!!!!!!!!!!!!!!!!!!!!!!!RequestScoped ??? !!!!!!!!!!!!!!!!!!!!
@ManagedBean
public class FeedbackBean {
    private String feedback;

    @Inject
    private ResultService resultService;

    private String testId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("testId");
    private String email = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");

    public String save() {
        boolean success = resultService.saveFeedback(email, testId, feedback);
        return success ? "userProfile?faces-redirect=true&email=" + email : "error";
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
