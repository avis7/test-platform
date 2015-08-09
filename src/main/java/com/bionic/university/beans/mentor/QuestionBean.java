package com.bionic.university.beans.mentor;

import com.bionic.university.entity.Answer;
import com.bionic.university.entity.Question;
import com.bionic.university.services.AnswerService;
import com.bionic.university.services.QuestionService;
import com.bionic.university.services.TestService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Артем on 09.08.2015.
 */

@SessionScoped
@ManagedBean(name = "questionBean")
public class QuestionBean {

    @Inject
    QuestionService questionService;
    @Inject
    AnswerService answerService;

    private String testId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("testId");

    private List<Question> questions;
    private List<Answer> answers;

    public List<Answer> getAnswers(int questionId) {
            answers=answerService.getAnswersForQuestion(String.valueOf(questionId));
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
}
