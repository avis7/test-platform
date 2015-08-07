package com.bionic.university.dao;

import com.bionic.university.entity.Answer;
import com.bionic.university.interceptor.TxInterceptorBinding;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by c266 on 28.07.2015.
 */
@TxInterceptorBinding
public class AnswerDAO extends AbstractDAO<Answer> {
    public AnswerDAO() {
        super(Answer.class);
    }

    public List<Answer> getAnswersByQuestionId(int questionId) {
        Query query = em.createNamedQuery("getAnswersByQuestionId");
        query.setParameter("question", questionId);
        return query.getResultList();
    }
}
