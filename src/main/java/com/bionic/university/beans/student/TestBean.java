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

    public List<TestRow> getTestRows() {
        return testService.getTestRows();
    }

    @PostConstruct
    public void fillTestTable(){
        testService.fillTestTable();
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
        if(testService.onRowEdit(event)){
            FacesMessage msg = new FacesMessage("Test Edited",
                    ((TestRow) event.getObject()).getTest().getTestName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "successful";
        }
        return "unsuccessful";
    }

    public String onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled",
                ((TestRow) event.getObject()).getTest().getTestName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
            return "successful";
    }

    public String exportTestResults(TestRow testRow){
        return "exportResultTest?faces-redirect=true&testId=" + String.valueOf(testRow.getTest().getId()) + "&testName=" + testRow.getTest().getTestName();
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }

}
