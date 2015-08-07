package com.bionic.university.services;

import com.bionic.university.dao.ResultDAO;
import com.bionic.university.dao.TestDAO;
import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.Result;
import com.bionic.university.entity.User;
import org.primefaces.event.RowEditEvent;

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
    @Inject
    private TestDAO testDAO;

    public boolean saveFeedback(String email, String testId, String feedback) {
        int test = Integer.valueOf(testId);
        int user = userDAO.findUserByEmail(email).getId();
        Result result = resultDAO.findResultByUserIdAndTestId(user, test);
        result.setFeedback(feedback);
        resultDAO.update(result);
        return true;
    }

    public boolean onRowEdit(RowEditEvent event, int selectedTest) {
        try {
            resultDAO.save(new Result((User) event.getObject(), testDAO.find(selectedTest)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Collection<Result> getResultByTestId(int testId) {
        return resultDAO.findResultByTestId(testId);
    }
}

