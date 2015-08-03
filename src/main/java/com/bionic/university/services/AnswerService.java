package com.bionic.university.services;

import com.bionic.university.dao.AnswerDAO;
import com.bionic.university.entity.Answer;
import com.bionic.university.entity.Question;

import javax.inject.Inject;

/**
 * Created by c266 on 28.07.2015.
 */
public class AnswerService {
    @Inject
    private AnswerDAO answerDAO;
    @Inject
    private QuestionService questionService;

    public boolean addAnswer(int questionId, String answerText, boolean isCorrect) {
        Answer answer = new Answer(answerText, isCorrect);
        Question question = questionService.getQuestionDAO().find(questionId);
        answer.setQuestion(question);
        answerDAO.save(answer);
        return true;
        //TODO refresh page
    }

    public boolean editAnswer(int answerId, String answerText, boolean isCorrect){
        Answer answer = answerDAO.find(answerId);
        answer.setAnswerText(answerText);
        answer.setIsCorrect(isCorrect);
        answerDAO.save(answer);
        return true;
        //TODO refresh page
    }
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public boolean deleteAnswer(int answerId){
        Answer answer = answerDAO.find(answerId);
        answerDAO.delete(answer);
        return true;
        //TODO refresh page
    }
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public AnswerDAO getAnswerDAO() {
        return answerDAO;
    }

    public void setAnswerDAO(AnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }
}
