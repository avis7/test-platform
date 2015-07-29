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
    TestDAO testDAO;

    public boolean addTest(String testName, Date duration, Date deadline) {
        try {
            Test test = new Test(testName, duration, deadline);
            testDAO.save(test);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Test findTestByName(String testName) {
        try {
            Test test = testDAO.findTestByName(testName);
            return test;
        } catch (Exception e) {
        }
        return null;
    }

    public boolean editTest(long id, String newTestName, Date Duration, Date Deadline) {
        try {
            testDAO.update(testDAO.find(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteTest(Test test) {
        try {
            testDAO.delete(test);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
