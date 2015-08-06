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
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Olexandr on 7/30/2015.
 */
@SessionScoped   //!!!!!!!!!!!!!!!!!!!!!!!RequestScoped ??? !!!!!!!!!!!!!!!!!!!!
@ManagedBean
public class TestBean {
    private List<Tab> tabs;
    private List<Question> questions;

    private String testId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("testId");
    private String email = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");

    @PostConstruct
    public void init() {
        questions = questionService.getQuestionsByTestId(testId);
        tabs = new ArrayList<Tab>();
        for (Question question : questions){
            tabs.add(new Tab(question));
        }
    }


    @Inject
    private QuestionService questionService;
    @Inject
    private UserAnswerService userAnswerService;

    public String submit(ActionEvent actionEvent) {
        boolean success = userAnswerService.save(email, testId, tabs);
        return success ? "feedback?testId=" + testId + "&email=" + email : "error.xhtml";
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

    public List<Tab> getTabs() {
        return tabs;
    }

    public void setTabs(List<Tab> tabs) {
        this.tabs = tabs;
    }

    public void addUserAnswer() {
        //if (answer == null) {
            //return;
        //}
       // userAnswerService.addUserAnswer(answer);
    }

    /*public void addUserAnswer(Answer answer, String ownAnswer) {
        userAnswerService.addUserAnswer(answer, ownAnswer);
    }

    public void addUserAnswer(List<Answer> answers) {
        userAnswerService.addUserAnswer(answers);
    }*/

    public class Tab{
        private Question question;
        private int answer;
        private String ownAnswer;
        private List<String> answers;

        public Tab(Question question) {
            this.question = question;
        }

        public Question getQuestion() {
            return question;
        }

        public void setQuestion(Question question) {
            this.question = question;
        }

        public int getAnswer() {
            return answer;
        }

        public void setAnswer(int answer) {
            this.answer = answer;
        }

        public String getOwnAnswer() {
            return ownAnswer;
        }

        public void setOwnAnswer(String ownAnswer) {
            this.answer = question.getAnswers().get(0).getId();
            this.ownAnswer = ownAnswer;
        }

        public List<String> getAnswers() {
            return answers;
        }

        public void setAnswers(List<String> answers) {
            this.answers = answers;
        }
    }


}
