package com.bionic.university.dao;

import com.bionic.university.entity.Result;
import com.bionic.university.interceptor.TxInterceptorBinding;

import javax.persistence.Query;
import java.util.List;

@TxInterceptorBinding
public class ResultDAO extends AbstractDAO<Result> {
    public ResultDAO() {
        super(Result.class);
    }
    public List<Result> findResultByTestId(int testId) {
        Query query = em.createNamedQuery("getQueryResultByTestId");
        query.setParameter("arg", testId);
        return query.getResultList();
    }
}
