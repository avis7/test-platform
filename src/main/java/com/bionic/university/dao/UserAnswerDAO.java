package com.bionic.university.dao;

import com.bionic.university.entity.UserAnswer;
import com.bionic.university.interceptor.TxInterceptorBinding;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Olexandr on 8/1/2015.
 */
@TxInterceptorBinding
public class UserAnswerDAO extends AbstractDAO<UserAnswer> {
    public UserAnswerDAO() {
        super(UserAnswer.class);
    }

    public List<UserAnswer> getUserAnswersByResultId(int resultId){
        Query query = em.createNamedQuery("getUserAnswersByResultId");
        query.setParameter("result", resultId);
        return query.getResultList();
    }
}
