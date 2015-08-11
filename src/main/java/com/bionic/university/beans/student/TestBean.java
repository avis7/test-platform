package com.bionic.university.beans.student;

import com.bionic.university.entity.Question;
import com.bionic.university.entity.Test;
import com.bionic.university.model.TestRow;
import com.bionic.university.services.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ViewScoped   //!!!!!!!!!!!!!!!!!!!!!!!RequestScoped ??? !!!!!!!!!!!!!!!!!!!!
@ManagedBean
public class TestBean {
    private List<Tab> tabs;
    private List<Question> questions;
    private String testName;
    private int duration;

    private String testId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("testId");
    private String email = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");


    @PostConstruct
    public void init() {
        try{
            if(!resultService.getResultByUserNameAndTestId(email, Integer.valueOf(testId)).isSubmited()){
                boolean success = userService.submitTest(testService.getTestByTestId(Integer.valueOf(testId)), email);
                questions = questionService.getQuestionsByTestId(testId);
                tabs = new ArrayList<Tab>();
                for (Question question : questions){
                    tabs.add(new Tab(question));
                }
                Test test = testService.getTestByTestId(Integer.valueOf(testId));
                testName = test.getTestName();
                duration = test.getDuration();
            }  else FacesContext.getCurrentInstance().getExternalContext().redirect( "index.xhtml");}
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String submit() {
        boolean success = userAnswerService.save(email, testId, tabs);
        return success ? "feedback?faces-redirect=true&testId=" + testId + "&email=" + email : "error";
    }


    @Inject
    private QuestionService questionService;
    @Inject
    private UserAnswerService userAnswerService;
    @Inject
    private ResultService resultService;
    private @Inject
    UserService userService;
    private @Inject
    TestService testService;



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

    public String exportTestResults(TestRow testRow){
        return "exportResultTest?faces-redirect=true&testId=" + String.valueOf(testRow.getTest().getId()) + "&testName=" + testRow.getTest().getTestName();
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}