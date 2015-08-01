package com.bionic.university.services;

import com.bionic.university.dao.TestDAO;
import com.bionic.university.entity.Test;
import com.bionic.university.entity.User;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by c266 on 28.07.2015.
 */
public class TestService {
    @Inject
     private TestDAO testDAO;

    public boolean addTest(String testName, Date duration, Date deadline, String categoryName) {
        try {
            Test test = new Test(testName, duration, deadline,categoryName);
            testDAO.save(test);
            return true;
        }catch (Exception e){
        return false;        }
    }

    public TestDAO getTestDAO() {
        return testDAO;
    }

    public void setTestDAO(TestDAO testDAO) {
        this.testDAO = testDAO;
    }

    public List<Test> getTestByUser(User user){
        return getTestByUserId(user.getId());
    }

    public List<Test> getTestByUserId(long userId) {
        try {
            return testDAO.findTestByUserId((int)userId);
        }catch (Exception e){return null;}
    }
}
