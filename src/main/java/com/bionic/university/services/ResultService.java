package com.bionic.university.services;

import com.bionic.university.dao.ResultDAO;
import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.Result;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    public List<Result> getResultByTestId(int testId) {
        return resultDAO.findResultByTestId(testId);
    }

    public List<Result> getSubmitedResults(){
        return resultDAO.findAllSubmitedResults();
    }




    public List<Result> sortByChecked(List<Result> results){
        Collections.sort(results, new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                boolean b1 = o1.isChecked();
                boolean b2 = o2.isChecked();
                if (b1) {
                    return 1;
                }
                if (b2) {
                    return -1;
                }
                return 0;
            }
        });
        return results;
    }

}

