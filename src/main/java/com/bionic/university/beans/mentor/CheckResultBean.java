package com.bionic.university.beans.mentor;

import com.bionic.university.entity.Question;
import com.bionic.university.entity.User;
import com.bionic.university.entity.UserAnswer;
import com.bionic.university.services.QuestionService;
import com.bionic.university.services.UserAnswerService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.*;

/**
 * Created by Olexandr on 8/9/2015.
 */
@ViewScoped
@ManagedBean
public class CheckResultBean {
    private List<UserAnswer> userAnswers;
    private List<Question> questions;

    @Inject
    private UserAnswerService userAnswerService;
    @Inject
    private QuestionService questionService;

    private String resultId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("resultId");

    @PostConstruct
    public  void  init(){
        userAnswers = userAnswerService.getUserAnswersWithOwnAnswerToCheckByResultId(resultId);
        if (userAnswers == null){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect( "mentorProfile.xhtml");
            }catch (Exception e){

            }

        }
        questions = new LinkedList<Question>();
        for (UserAnswer userAnswer : userAnswers){
            questions.add(questionService.getQuestionByAnswerId(userAnswer.getAnswerId()));
        }
    }

    public String save(){
        boolean success = userAnswerService.saveMarksForOwnAnswers(userAnswers, resultId);
        return success ? "mentorProfile?faces-redirect=true": "error";
    }

    public String cancel(){
        return "mentorProfile?faces-redirect=true";
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
