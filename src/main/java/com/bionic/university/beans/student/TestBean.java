package com.bionic.university.beans.student;

import com.bionic.university.entity.Answer;
import com.bionic.university.entity.Question;
import com.bionic.university.model.TestRow;
import com.bionic.university.services.QuestionService;
import com.bionic.university.services.TestService;
import com.bionic.university.services.UserAnswerService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ViewScoped
@ManagedBean
public class TestBean {
    private List<Question> questions;
    private Question currentQuestion;


    private String testId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("testId");
    private String email = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");

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
        return currentQuestion;
    }

    public List<Question> getQuestions() {
        questions = questionService.getQuestionsByTestId(testId);
        currentQuestion = questions.get(0);
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

    public void addUserAnswer(Answer answer) {
        userAnswerService.addUserAnswer(answer);
    }

    public void addUserAnswer(Answer answer, String ownAnswer) {
        userAnswerService.addUserAnswer(answer, ownAnswer);
    }

    public void addUserAnswer(List<Answer> answers) {
        userAnswerService.addUserAnswer(answers);
    }



}
