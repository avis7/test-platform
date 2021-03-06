package com.bionic.university.services;

import com.bionic.university.dao.QuestionDAO;
import com.bionic.university.entity.Question;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by c266 on 28.07.2015.
 */
public class QuestionService {
    @Inject
    private QuestionDAO questionDAO;
    @Inject
    private TestService testService;



    public QuestionDAO getQuestionDAO() {
        return questionDAO;
    }

    public void setQuestionDAO(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }
    
    public List<Question> getQuestionsByTestId(String stringTestId){
        try {
            int testId = Integer.valueOf(stringTestId);
            return questionDAO.getQuestionsByTestId(testId);
        }catch (NumberFormatException e1){}
        catch (Exception e2){}
        return null;
    }

    public boolean addQuestion(String questionText, int mark) {
        Question question = new Question(questionText, mark, false, false);
        questionDAO.save(question);
        return true;
    }

    public boolean deleteQuestion(int questionId) {
        Question question = questionDAO.find(questionId);
        questionDAO.delete(question);
        return true;
    }

    public boolean updateQuestion(int questionId, String questionText, int mark, boolean isMultiChoice, boolean isOpen) {
        Question question = questionDAO.find(questionId);
        question.setQuestion(questionText);
        question.setMark(mark);
        question.setIsOpen(isOpen);
        question.setIsMultichoise(isMultiChoice);
        questionDAO.update(question);
        return true;
    }

    public Question getQuestionByAnswerId(int answerId){
        try {
            return questionDAO.getQuestionByAnswerId(answerId);
        }catch (Exception e){}
        return null;
    }
}
