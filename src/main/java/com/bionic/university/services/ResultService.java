package com.bionic.university.services;

import com.bionic.university.dao.ResultDAO;
import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.Result;

import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by c266 on 28.07.2015.
 */

public class ResultService {
    @Inject
    private ResultDAO resultDAO;
    @Inject
    private UserDAO userDAO;

    public boolean saveFeedback(String email, String testId, String feedback){
        int test = Integer.valueOf(testId);
        int user = userDAO.findUserByEmail(email).getId();
        Result result = resultDAO.findResultByUserIdAndTestId(user, test);
        result.setFeedback(feedback);
        resultDAO.update(result);
        return true;
    }

    public Collection<Result> getResultByTestId(int testId) {
        return resultDAO.findResultByTestId(testId);
    }
}

