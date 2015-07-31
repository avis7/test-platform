package com.bionic.university.services;

import com.bionic.university.dao.QuestionDAO;
import com.bionic.university.entity.Question;
import com.bionic.university.entity.Test;

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



    public boolean addQuestion(int testId, String question, int mark, boolean isOpen, boolean isMultichoise) {
        Question questionToSave = new Question(question, mark, isOpen, isMultichoise);
        Test test = testService.getTestDAO().find(testId);
        questionToSave.setTest(test);
        questionDAO.save(questionToSave);
        return true;
        //TODO refresh page
    }

    public boolean editQuestion(int questionId, String question, int mark, boolean isOpen, boolean isMultichoise){
        Question questionToUpdate = questionDAO.find(questionId);
        questionToUpdate.setQuestion(question);
        questionToUpdate.setMark(mark);
        questionToUpdate.setIsOpen(isOpen);
        questionToUpdate.setIsMultichoise(isMultichoise);
        questionDAO.update(questionToUpdate);
        return true;
        //TODO refresh page
    }
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public boolean deleteQuestion(int questionId){
        Question questiontoDelete = questionDAO.find(questionId);
        questionDAO.delete(questiontoDelete);
        return true;
        //TODO refresh page
    }
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public QuestionDAO getQuestionDAO() {
        return questionDAO;
    }

    public void setQuestionDAO(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public List<Question> getQuestionsByTestId(long testId){
        try {
            return questionDAO.findQuestionByTestId((int)testId);
        }catch (Exception e){return null;}
    }

    public List<Question> getQuestionsByTest(Test test){
        return getQuestionsByTestId(test.getId());
    }

}
