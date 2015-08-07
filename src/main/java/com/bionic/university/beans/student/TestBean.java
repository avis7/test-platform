package com.bionic.university.beans.student;

import com.bionic.university.entity.Answer;
import com.bionic.university.entity.Question;
import com.bionic.university.model.TestRow;
import com.bionic.university.services.QuestionService;
import com.bionic.university.services.TestService;
import com.bionic.university.services.UserAnswerService;
import org.primefaces.event.RowEditEvent;

import org.hibernate.Session;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import javax.annotation.PostConstruct;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;

@ViewScoped   //!!!!!!!!!!!!!!!!!!!!!!!RequestScoped ??? !!!!!!!!!!!!!!!!!!!!
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
        testService.fillTestTable();
    }


    @Inject
    private QuestionService questionService;
    @Inject
    private UserAnswerService userAnswerService;
    @Inject
    TestService testService;

    private Date deadline;
    private Integer duration;
    private String testName;
    private String categoryName;

    public boolean isVisible() {
        return testService.getVisible();
    }

    public void setVisible() {
        testService.setVisible(true);
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

   
    public String submit() {
        boolean success = userAnswerService.save(email, testId, tabs);
        return success ? "feedback?faces-redirect=true&testId=" + testId + "&email=" + email : "error";
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

    public List<TestRow> getTestRows() {
        return testService.getTestRows();
    }

    public String addTest(){
        if (testService.addTest(testName, duration, deadline, categoryName))
            return "successful";
        return "unsuccessful";
    }

    public String deleteTest(TestRow testRow){
        if(testService.deleteTest(testRow))
            return "successful";
        return "unsuccessful";
    }
    public String onRowEdit(RowEditEvent event) {
        if(testService.onRowEdit(event))
            return "successful";
        return "unsuccessful";
    }

    public String onRowCancel(RowEditEvent event) {
        if(testService.onRowCancel(event))
            return "successful";
        return "unsuccessful";
    }

    public String exportTestResults(TestRow testRow){
        return "exportResultTest?faces-redirect=true&testId=" + String.valueOf(testRow.getTest().getId()) + "&testName=" + testRow.getTest().getTestName();
    }

}