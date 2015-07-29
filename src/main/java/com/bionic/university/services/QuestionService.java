package com.bionic.university.services;

import com.bionic.university.dao.QuestionDAO;
import com.bionic.university.entity.Question;

import javax.inject.Inject;

/**
 * Created by c266 on 28.07.2015.
 */
public class QuestionService {
    @Inject
    QuestionDAO questionDAO;

    public void addQuestion(String question, int mark, boolean isOpen, boolean isMultichoise){
       try {
           Question questionToSave = new Question(question, mark, isOpen, isMultichoise);
       }catch (Exception e){}
    }
}
