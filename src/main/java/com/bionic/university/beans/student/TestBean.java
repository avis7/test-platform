package com.bionic.university.beans.student;

import com.bionic.university.entity.Answer;
import com.bionic.university.entity.Question;
import com.bionic.university.model.TestRow;
import com.bionic.university.services.QuestionService;
import com.bionic.university.services.UserAnswerService;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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

    public String submit(){
        boolean success = userAnswerService.save(email, testId);
        return success ? "feedback?testId=" + testId + "&email=" +email : "error.xhtml";
    }

    public void setCurrentQuestion(Question question){
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

    public void addUserAnswer(Answer answer){
        userAnswerService.addUserAnswer(answer);
    }

    public void addUserAnswer(Answer answer, String ownAnswer){
        userAnswerService.addUserAnswer(answer, ownAnswer);
    }

    public void addUserAnswer(List<Answer> answers){
        userAnswerService.addUserAnswer(answers);
    }

    public String exportTestResults(TestRow testRow){
        return "exportResultTest?faces-redirect=true&testId=" + String.valueOf(testRow.getTest().getId()) + "&testName=" + testRow.getTest().getTestName();
    }

}