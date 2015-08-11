package com.bionic.university.beans.mentor;

import com.bionic.university.entity.Answer;
import com.bionic.university.entity.Question;
import com.bionic.university.services.AnswerService;
import com.bionic.university.services.QuestionService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Артем on 09.08.2015.
 */

@ViewScoped
@ManagedBean(name = "questionBean")
public class QuestionAnswerBean {

    @Inject
    QuestionService questionService;
    @Inject
    AnswerService answerService;

    private String testId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("testId");

    private String answerText;
    private String questionText;
    private boolean isCorrect;
    private int mark;
    private boolean multichoice;
    private boolean visibleAnswer;
    private boolean open;

    public boolean isVisibleQuestion() {
        return questionService.isVisibleQuestion();
    }

    public void setVisibleQuestion() {
        clearQuestionFields();
        questionService.setVisibleQuestion(true);
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isVisibleAnswer() {
        return visibleAnswer;
    }

    public void setVisibleAnswer() {
        this.visibleAnswer = true;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public boolean isMultichoice() {
        return multichoice;
    }

    public void setMultichoice(boolean multichoice) {
        this.multichoice = multichoice;
    }

    public List<Answer> getAnswers(Question question) {
        return answerService.getAnswers(question);
    }

    public void setAnswers(List<Answer> answers) {
        answerService.setAnswers(answers);
    }

    public List<Question> getQuestions() {
        /*if(questions==null)
            questions=questionService.getQuestionsByTestId(testId);*/
        return questionService.getQuestions();
    }

    @PostConstruct
    public void fillTable(){
        questionService.fillQuestionTable(testId);
    }

    public void setQuestions(List<Question> questions) {
        questionService.setQuestions(questions);
    }

    public String deleteQuestion(Question question){
        if (questionService.deleteQuestion(question)) {
            if (question.isArchived())
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Question" + question.getQuestion() + " was archived", null));
            else FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Question" + question.getQuestion()+ " was unarchived", null));
            return "successful";
        }
        return "unsuccessful";
    }

    public String deleteAnswer(Answer answer) {
        if (answerService.deleteAnswer(answer)){
            if (answer.isArchived())
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Answer" +answer.getAnswerText()+ " was archived", null));
            else FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Answer" +answer.getAnswerText()+ " was unarchived", null));
            return "successful";
        }
        return "unsuccessful";
    }

    public String addAnswer(Question question, String answerText, boolean isCorrect){
        if(answerService.addAnswer(question, answerText, isCorrect)){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Answer was added", null));
        return "successful";
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Answer wasn't added", null));
        return "unsuccessful";
    }

    public String addQuestion(String testId, String question, int mark, boolean isOpen, boolean isMultichoise){
        if(questionService.addQuestion(testId, question, mark, isOpen, isMultichoise)){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Question was added", null));
            return "successful";
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Question wasn't added", null));
        return "unsuccessful";
    }

    public String onRowEditQuestion(RowEditEvent event) {
        if (questionService.onRowEdit(testId, event)) {
            FacesMessage msg = new FacesMessage("Question Edited",
                    ((Question) event.getObject()).getQuestion());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "successful";
        }
        FacesMessage msg = new FacesMessage("Question is not edited",
                ((Question) event.getObject()).getQuestion());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "unsuccessful";
    }

    public String onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "successful";
    }

    public String onRowEditAnswer(RowEditEvent event) {
        if (answerService.onRowEdit(event)) {
            FacesMessage msg = new FacesMessage("Answer Edited",
                    ((Answer) event.getObject()).getAnswerText());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "successful";
        }
        FacesMessage msg = new FacesMessage("Answer is not edited",
                ((Answer) event.getObject()).getAnswerText());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "unsuccessful";
    }

    public void clearQuestionFields(){
        questionText=null;
        mark=0;
    }
}
