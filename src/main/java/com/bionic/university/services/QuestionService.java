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
        int testId = Integer.valueOf(stringTestId);
        return questionDAO.getQuestionsByTestId(testId);
    }

    public boolean addQuestion(String questionText, int mark) {
        Question question = new Question(questionText, mark, false, false);
        questionDAO.save(question);
        return true;
    }

    public boolean deleteQuestion(long questionId) {
        Question question = questionDAO.find(questionId);
        questionDAO.delete(question);
        return true;
    }

    public boolean updateQuestion(long questionId, String questionText, int mark, boolean isMultiChoice, boolean isOpen) {
        Question question = questionDAO.find(questionId);
        question.setQuestion(questionText);
        question.setMark(mark);
        question.setIsOpen(isOpen);
        question.setIsMultichoise(isMultiChoice);
        questionDAO.update(question);
        return true;
    }
}
