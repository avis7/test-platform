package com.bionic.university.services;

import com.bionic.university.dao.AnswerDAO;
import com.bionic.university.entity.Answer;
import com.bionic.university.entity.Question;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by c266 on 28.07.2015.
 */
public class AnswerService {
    @Inject
    private AnswerDAO answerDAO;
    @Inject
    private QuestionService questionService;

    public List<Answer> getAnswersByQuestionId(String stringQuestionId) {
        int questionId = Integer.valueOf(stringQuestionId);
        return answerDAO.getAnswersByQuestionId(questionId);
    }

    public boolean addAnswer(int questionId, String answerText, boolean isCorrect) {
        Answer answer = new Answer(answerText, isCorrect);
        Question question = questionService.getQuestionDAO().find(questionId);
        //answer.linkToQuestion(question);
        answerDAO.save(answer);
        return true;
    }

    public boolean updateAnswer(int answerId, String answerText, boolean isCorrect){
        Answer answer = answerDAO.find(answerId);
        answer.setAnswerText(answerText);
        answer.setIsCorrect(isCorrect);
        answerDAO.save(answer);
        return true;
    }

    public boolean deleteAnswer(int answerId){
        Answer answer = answerDAO.find(answerId);
        answerDAO.delete(answer);
        return true;
    }

    public String getStringAnswerById(int answerId){
        try {
            Answer answer = answerDAO.find(answerId);
            return answer.getAnswerText();
        }catch (Exception e){}
        return null;
    }
}
