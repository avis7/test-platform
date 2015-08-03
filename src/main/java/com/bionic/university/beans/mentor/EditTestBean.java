package com.bionic.university.beans.mentor;

import com.bionic.university.entity.Question;
import com.bionic.university.services.QuestionService;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Olexandr on 8/3/2015.
 */
public class EditTestBean {
    private List<Question> questions;

    @Inject
    private QuestionService questionService;

    private String testId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("testId");

//    public String createQuestion(){
//        questionService.
//    }



    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
