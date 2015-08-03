package com.bionic.university.dao;

import com.bionic.university.entity.Question;
import com.bionic.university.entity.Test;
import com.bionic.university.interceptor.TxInterceptorBinding;

import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * Created by c266 on 28.07.2015.
 */
@TxInterceptorBinding
public class QuestionDAO extends AbstractDAO<Question>{
    public QuestionDAO() {
        super(Question.class);
    }

    public Collection<Question> findQuestionsByTest(Test test){
        //TODO
        return null;
    }
    public List<Question> findQuestionByTestId(int testId) {
        Query query = em.createNamedQuery("getQueryQuestionByTestId");
        query.setParameter("arg", testId);
        return query.getResultList();
    }
}
