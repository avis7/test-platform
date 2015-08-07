package com.bionic.university.dao;

import com.bionic.university.entity.Result;
import com.bionic.university.interceptor.TxInterceptorBinding;

import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

@TxInterceptorBinding
public class ResultDAO extends AbstractDAO<Result> {
    public ResultDAO() {
        super(Result.class);
    }

    public Result findResultByUserIdAndTestId(int userId, int testId){
        Query query = em.createNamedQuery("findResultByUserIdAndTestId");
        query.setParameter("user", userId);
        query.setParameter("test", testId);
        return (Result) query.getSingleResult();
    }

    public List<Result> findResultByTestId(int testId) {
        Query query = em.createNamedQuery("findResultByTestId");
        query.setParameter("test", testId);
        return query.getResultList();
    }

    public List<Result> findAllSubmitedResults(){
        Query query = em.createNamedQuery("findAllSubmitedResults");
        return (List<Result>) query.getResultList();


    }
}
