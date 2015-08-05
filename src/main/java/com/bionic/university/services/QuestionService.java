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















}
