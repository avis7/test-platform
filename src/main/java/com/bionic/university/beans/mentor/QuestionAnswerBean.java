package com.bionic.university.beans.mentor;

import com.bionic.university.entity.Answer;
import com.bionic.university.entity.Question;
import com.bionic.university.services.AnswerService;
import com.bionic.university.services.QuestionService;

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

    private List<Question> questions;
    private List<Answer> answers;
    private String AnswerText;
    private String QuestionText;
    private boolean isCorrect;
    private int mark;
    private boolean isOpen;
    private boolean isMultichoice;
    private boolean visibleAnswer;
    private boolean visibleQuestion;

    public boolean isVisibleQuestion() {
        return visibleQuestion;
    }

    public void setVisibleQuestion() {
        this.visibleQuestion = true;
    }

    public boolean isVisibleAnswer() {
        return visibleAnswer;
    }

    public void setVisibleAnswer() {
        this.visibleAnswer = true;
    }

    public String getAnswerText() {
        return AnswerText;
    }

    public void setAnswerText(String answerText) {
        AnswerText = answerText;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public void setQuestionText(String questionText) {
        QuestionText = questionText;
    }

    public boolean isCorrect() {
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

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isMultichoice() {
        return isMultichoice;
    }

    public void setIsMultichoice(boolean isMultichoice) {
        this.isMultichoice = isMultichoice;
    }

    public List<Answer> getAnswers(Question question) {
            answers=answerService.getAnswersForQuestion(String.valueOf(question.getId()));
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Question> getQuestions() {
            questions=questionService.getQuestionsByTestId(testId);
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void editQuestion(Question question){

    }

    public void editAnswer(Answer answer){

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
        if(answerService.addAnswer(question,answerText,isCorrect))
        return "successful";
        return "unsuccessful";
    }

    public String addQuestion(String question, int mark, boolean isOpen, boolean isMultichoise){
        if(questionService.addQuestion(question,mark,isOpen,isMultichoise))
        return "successful";
        return "unsuccessful";
    }
}
