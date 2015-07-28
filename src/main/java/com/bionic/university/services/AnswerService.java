package com.bionic.university.services;

import com.bionic.university.dao.AnswerDAO;
import com.bionic.university.entity.Answer;

import javax.inject.Inject;

/**
 * Created by c266 on 28.07.2015.
 */
public class AnswerService {
    @Inject
    AnswerDAO answerDAO;

    public void addAnswer(boolean isCorrect){
        try {
            Answer answer = new Answer(isCorrect);
            answerDAO.save(answer);
        }catch (Exception e){}
    }
}
