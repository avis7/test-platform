package com.bionic.university.services;

import com.bionic.university.dao.ResultDAO;
import com.bionic.university.entity.Result;
import com.bionic.university.entity.Test;
import com.bionic.university.entity.User;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by c266 on 28.07.2015.
 */

public class ResultService {
    @Inject
    ResultDAO resultDAO;
    @Inject
    TestService testService;


    public void addResults(User user, Collection<Test> tests) {
        Iterator<Test> testIterator = tests.iterator();
        while (testIterator.hasNext()) {
            Test test = testService.getTestDAO().find(testIterator.next().getId());
            Result result = new Result(user, test);
            resultDAO.save(result);
        }
    }

    public List<Result> getResultsByUserId(long testId){
        try {
            return resultDAO.findResultByTestId((int)testId);
        }catch (Exception e){return null;}
    }

    public List<Result> getResultsByUser(Test test){
        return getResultsByUserId(test.getId());
    }

}

