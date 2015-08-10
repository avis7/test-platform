package com.bionic.university.services;

import com.bionic.university.dao.AnswerDAO;
import com.bionic.university.entity.Answer;
import com.bionic.university.entity.Question;
import org.primefaces.event.RowEditEvent;

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

    public List<Answer> getAnswersForQuestion(String stringQuestionId) {
        int questionId = Integer.valueOf(stringQuestionId);
        return answerDAO.getAnswersByQuestionId(questionId);
    }

    public boolean addAnswer(int questionId, String answerText, boolean isCorrect) {
        try {
            Answer answer = new Answer(answerText, isCorrect);
            Question question = questionService.getQuestionDAO().find(questionId);
            answerDAO.save(answer);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean addAnswer(Question question, String answerText, boolean isCorrect) {
        try {
            Answer answer = new Answer(answerText, isCorrect);
            answer.setQuestion(question);
            answerDAO.save(answer);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateAnswer(int answerId, String answerText, boolean isCorrect){
        Answer answer = answerDAO.find(answerId);
        answer.setAnswerText(answerText);
        answer.setIsCorrect(isCorrect);
        answerDAO.save(answer);
        return true;
    }

    public AnswerDAO getAnswerDAO() {
        return answerDAO;
    }

    public void setAnswerDAO(AnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }

    public boolean deleteAnswer(Answer answer){
        try {
            if (answer.isArchived())
                answer.setArchived(false);
            else answer.setArchived(true);
            answerDAO.update(answer);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean onRowEdit(RowEditEvent event) {
        try {
            answerDAO.update((Answer) event.getObject());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
