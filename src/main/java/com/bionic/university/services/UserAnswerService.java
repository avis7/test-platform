package com.bionic.university.services;

import com.bionic.university.beans.student.TestBean;
import com.bionic.university.dao.*;
import com.bionic.university.entity.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Olexandr on 8/1/2015.
 */
public class UserAnswerService {

    @Inject
    private UserAnswerDAO userAnswerDAO;
    @Inject
    private AnswerDAO answerDAO;
    @Inject
    private ResultDAO resultDAO;
    @Inject
    private UserDAO userDAO;
    @Inject
    private QuestionDAO questionDAO;

    public boolean save(String email, String testId, List<TestBean.Tab> tabs) {
        int mark = 0;

        try {
            int test = Integer.valueOf(testId);
            int user = userDAO.findUserByEmail(email).getId();
            Result result = resultDAO.findResultByUserIdAndTestId(user, test);
            User user1 = userDAO.find(8);
            System.out.println(user1.getPassword());
            for (TestBean.Tab tab : tabs) {
                if (!tab.getQuestion().getIsMultichoise() && !tab.getQuestion().getIsOpen()) {
                    UserAnswer userAnswer = new UserAnswer(tab.getAnswer());
                    userAnswer.setResult(result);
                    userAnswerDAO.save(userAnswer);
                    mark += calculateMarkForOneQuestion(tab);
                }
                if (tab.getQuestion().getIsMultichoise()) {
                    for (String answer : tab.getAnswers()) {
                        UserAnswer userAnswer = new UserAnswer(Integer.valueOf(answer));
                        userAnswer.setResult(result);
                        userAnswerDAO.save(userAnswer);
                    }
                    mark += calculateMarkForManyQuestion(tab);
                }
                if (tab.getQuestion().getIsOpen()) {
                    UserAnswer userAnswer = new UserAnswer(tab.getAnswer());
                    userAnswer.setOwnAnswer(tab.getOwnAnswer());
                    userAnswer.setResult(result);
                    userAnswerDAO.save(userAnswer);
                }
            }
            result.setMark(mark);
            resultDAO.update(result);
        } catch (IllegalArgumentException e) {
            return false;
        } catch (Exception e1){
            System.out.println();
        }
        return true;
    }

    private int calculateMarkForOneQuestion(TestBean.Tab tab) {
        try {
            Answer answer = answerDAO.find(tab.getAnswer());
            if (answer.isCorrect()) {
                return answer.getQuestion().getMark();
            }
        } catch (Exception e) {

        }
        return 0;
    }

    private int calculateMarkForManyQuestion(TestBean.Tab tab) {
        boolean correct = true;
        Question question = null;
        try {
            for (String ans : tab.getAnswers()) {
                Answer answer = answerDAO.find(Integer.valueOf(ans));
                if (question == null)
                    question = answer.getQuestion();
                if (!answer.isCorrect()) {
                    correct = false;
                }
            }
            if (correct && question != null) {
                int correctAnswers = 0;
                for (Answer answer : question.getAnswers()){
                    if (answer.isCorrect()){
                        correctAnswers++;
                    }
                }
                if (correctAnswers == tab.getAnswers().size()) {
                    return question.getMark();
                }
            }
        }catch (Exception e){

        }
        return 0;
    }


}
