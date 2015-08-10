package com.bionic.university.services;

import com.bionic.university.dao.ResultDAO;
import com.bionic.university.dao.TestDAO;
import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.Result;
import com.bionic.university.entity.Test;
import com.bionic.university.entity.User;
import org.primefaces.event.RowEditEvent;

import javax.inject.Inject;
import java.util.*;

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

    public int onRowEdit(RowEditEvent event, int selectedTest) {
        try {
            Collection<Test> tests = ((User) event.getObject()).getTests();
            for (Test test : tests) {
                if (test.getId() == selectedTest)
                    return 2;
            }
            resultDAO.save(new Result((User) event.getObject(), testDAO.find(selectedTest)));
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 3;
        }
    }
    public boolean saveFeedback(String email, String testId, String feedback){
        try {
            int test = Integer.valueOf(testId);
            int user = userDAO.findUserByEmail(email).getId();
            Result result = resultDAO.findResultByUserIdAndTestId(user, test);
            result.setFeedback(feedback);
            resultDAO.update(result);
            return true;
        }catch (Exception e){

        }
        return false;
    }

    public List<Result> getResultByTestId(int testId) {
        return resultDAO.findResultByTestId(testId);
    }

    public List<Result> getResultsToCheckByTestId(String testId) {
        try {
            List<Result> results;
            List<Result> resultsToCheck = new LinkedList<Result>();
            int test = Integer.valueOf(testId);
            results = (List<Result>)resultDAO.findResultByTestId(test);
            for (Result result : results){
                if (result.isSubmited() && !result.isChecked()){
                    resultsToCheck.add(result);
                }
            }
            return resultsToCheck;
        }catch (NumberFormatException e1){}
        catch (Exception e2){}
        return null;

    }

    public List<Result> getAllSubmitedCheckedResults(){
        return resultDAO.findAllSubmitedCheckedResults();
    }
}

