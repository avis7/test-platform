package com.bionic.university.services;

import com.bionic.university.dao.TestDAO;
import com.bionic.university.entity.Test;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by c266 on 28.07.2015.
 */
public class TestService {
    @Inject
     private TestDAO testDAO;

    public void addTest(String testName, int duration, Date deadline) {
        Test test = new Test(testName, duration, deadline);
        testDAO.save(test);

    }

    public TestDAO getTestDAO() {
        return testDAO;
    }

    public void setTestDAO(TestDAO testDAO) {
        this.testDAO = testDAO;
    }
}
