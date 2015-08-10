package com.bionic.university.beans.mentor;

import com.bionic.university.entity.Question;
import com.bionic.university.entity.UserAnswer;
import com.bionic.university.services.AnswerService;
import com.bionic.university.services.QuestionService;
import com.bionic.university.services.UserAnswerService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Olexandr on 8/9/2015.
 */
@ViewScoped
@ManagedBean
public class viewCheckedResultBean {
    private List<UserAnswer> userAnswers;
    private List<Question> questions;

    @Inject
    private UserAnswerService userAnswerService;
    @Inject
    private QuestionService questionService;
    @Inject
    private AnswerService answerService;

    private String resultId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("resultId");

    @PostConstruct
    public  void  init(){
        userAnswers = userAnswerService.getUserAnswersByResultId(resultId);
        questions = new LinkedList<Question>();
        for (UserAnswer userAnswer : userAnswers){
            questions.add(questionService.getQuestionByAnswerId(userAnswer.getId()));
        }
        for (Question question : questions){
            for (Question question1 : questions){

            }
        }
    }

    public String getAnswer(int answerId){
        String answer = answerService.getStringAnswerById(answerId);
        return answer != null ? answer : "couldn't found answer with such id";
    }

    public List<String> getAnswers(int questionId){
        List<String> strUserAnswers = userAnswerService.getUserAnswersByQuestionId(questionId, userAnswers);
        if (strUserAnswers != null)
            return strUserAnswers;
        return null;
    }


    public List<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
