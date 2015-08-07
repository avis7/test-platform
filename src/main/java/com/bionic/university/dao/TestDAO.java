package com.bionic.university.dao;

import com.bionic.university.entity.Test;
import com.bionic.university.interceptor.TxInterceptorBinding;

import javax.persistence.Query;
import java.util.List;

@TxInterceptorBinding
public class TestDAO extends AbstractDAO<Test> {
    public TestDAO() {
        super(Test.class);
    }

    public Test findTestByName(String name){
        //TODO
        return new Test();
    }

    public List<Test> getVisibleTests(){
        Query query = em.createNamedQuery("getVisibleTests");
        return query.getResultList();
    }

}
