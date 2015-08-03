package com.bionic.university.dao;

import com.bionic.university.entity.Test;
import com.bionic.university.interceptor.TxInterceptorBinding;

@TxInterceptorBinding
public class TestDAO extends AbstractDAO<Test> {
    public TestDAO() {
        super(Test.class);
    }

    public Test findTestByName(String name){
        //TODO
        return new Test();
    }
}
