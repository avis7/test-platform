package com.bionic.university.dao;

import com.bionic.university.entity.Question;
import com.bionic.university.interceptor.TxInterceptorBinding;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by c266 on 28.07.2015.
 */
@TxInterceptorBinding
public class QuestionDAO extends AbstractDAO<Question> {
    public QuestionDAO() {
        super(Question.class);
    }

    public List<Question> getQuestionsByTestId(int testId) {
        Query query = em.createNamedQuery("getQuestionsByTestId");
        query.setParameter("test", testId);
        return query.getResultList();
    }

    public Question getQuestionByText(String questionText) {
        Query query = em.createNamedQuery("getQuestionByText");
        query.setParameter("questionText", questionText);
        return (Question) query.getSingleResult();
    }

    public Question getQuestionByAnswerId(int answerId){
        Query query = em.createNamedQuery("getQuestionByAnswerId");
        query.setParameter("answer", answerId);
        return (Question)query.getSingleResult();
    }
}
