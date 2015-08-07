package com.bionic.university.beans.mentor;

import com.bionic.university.entity.Answer;
import com.bionic.university.entity.Question;
import com.bionic.university.services.AnswerService;
import com.bionic.university.services.QuestionService;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Olexandr on 8/3/2015.
 */
public class EditTestBean {
    private List<Question> questions;
    private List<List<Answer>> answersToQuestions;

    @Inject
    private QuestionService questionService;

    @Inject
    private AnswerService answerService;

    private String testId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("testId");
    private String questionField = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("questionText");

//    public String createQuestion(){
//        questionService.
//    }

    public void addQuestion() {
        questionService.addQuestion(questionField, 0);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<List<Answer>> getAnswersToQuestions() {
        return answersToQuestions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setAnswersToQuestions(List<List<Answer>> answersToQuestions) {
        this.answersToQuestions = answersToQuestions;
    }

    public List<Answer> getAnswersToQuestion(int id) {
        return getAnswersToQuestions().get(id);
    }
}
