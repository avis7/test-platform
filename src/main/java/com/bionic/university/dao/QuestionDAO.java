package com.bionic.university.dao;

import com.bionic.university.entity.Question;
import com.bionic.university.entity.Test;

import java.util.Collection;

/**
 * Created by c266 on 28.07.2015.
 */
public class QuestionDAO extends AbstractDAO<Question>{
    public QuestionDAO() {
        super(Question.class);
    }

    public Collection<Question> findQuestionsByTest(Test test){
        //TODO
        return null;
    }
}
