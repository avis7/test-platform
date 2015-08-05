package com.bionic.university.beans.student;

import com.bionic.university.entity.Answer;
import com.bionic.university.entity.Question;
import com.bionic.university.services.QuestionService;
import com.bionic.university.services.UserAnswerService;

import org.hibernate.Session;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import java.util.List;
import java.util.Set;

/**
 * Created by Olexandr on 7/30/2015.
 */
@SessionScoped   //!!!!!!!!!!!!!!!!!!!!!!!RequestScoped ??? !!!!!!!!!!!!!!!!!!!!
@ManagedBean
public class TestBean {
    private List<Question> questions;
    private Question currentQuestion;

    private String testId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("testId");
    private String email = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");

    private Answer answer;


    @PostConstruct
    public void init() {
        questions = questionService.getQuestionsByTestId(testId);
    }


    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }


    @Inject
    private QuestionService questionService;
    @Inject
    private UserAnswerService userAnswerService;

    public String submit() {
        boolean success = userAnswerService.save(email, testId);
        return success ? "feedback?testId=" + testId + "&email=" + email : "error.xhtml";
    }

    public void setCurrentQuestion(Question question) {
        currentQuestion = question;
    }

    public Question getCurrentQuestion() {
        if (currentQuestion == null) {
            currentQuestion = questions.get(0);
        }
        return currentQuestion;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public void addUserAnswer() {
        if (answer == null) {
            return;
        }
        userAnswerService.addUserAnswer(answer);
    }

    public void addUserAnswer(Answer answer, String ownAnswer) {
        userAnswerService.addUserAnswer(answer, ownAnswer);
    }

    public void addUserAnswer(List<Answer> answers) {
        userAnswerService.addUserAnswer(answers);
    }


}
