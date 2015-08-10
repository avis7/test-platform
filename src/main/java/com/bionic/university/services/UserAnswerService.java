package com.bionic.university.services;

import com.bionic.university.beans.student.TestBean;
import com.bionic.university.dao.*;
import com.bionic.university.entity.*;

import javax.inject.Inject;
import java.util.*;

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
    @Inject
    private AnswerService answerService;

    public boolean save(String email, String testId, List<TestBean.Tab> tabs) {
        Date passTime = new Date();
        int mark = 0;

        try {
            int test = Integer.valueOf(testId);
            int user = userDAO.findUserByEmail(email).getId();
            Result result = resultDAO.findResultByUserIdAndTestId(user, test);

            for (TestBean.Tab tab : tabs) {
                if (!tab.getQuestion().getIsMultichoise() && !tab.getQuestion().getIsOpen()) {
                    UserAnswer userAnswer = new UserAnswer(tab.getAnswer());
                    userAnswer.setResult(result);
                    userAnswer.setMark(calculateMarkForOneQuestion(tab));
                    mark += userAnswer.getMark();
                    userAnswerDAO.save(userAnswer);
                }
                if (tab.getQuestion().getIsMultichoise()) {
                    int markToWrite = calculateMarkForManyQuestion(tab);
                    for (String answer : tab.getAnswers()) {
                        UserAnswer userAnswer = new UserAnswer(Integer.valueOf(answer));
                        userAnswer.setResult(result);
                        userAnswer.setMark(markToWrite);
                        userAnswerDAO.save(userAnswer);
                    }
                    mark += markToWrite;
                }
                if (tab.getQuestion().getIsOpen()) {
                    UserAnswer userAnswer = new UserAnswer(tab.getAnswer());
                    userAnswer.setOwnAnswer(tab.getOwnAnswer());
                    userAnswer.setResult(result);
                    userAnswerDAO.save(userAnswer);
                }
            }
            result.setMark(mark);
            result.setPassTime(passTime);
            resultDAO.update(result);
        } catch (IllegalArgumentException e) {
            return false;
        } catch (Exception e1) {
            return false;
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
                for (Answer answer : question.getAnswers()) {
                    if (answer.isCorrect()) {
                        correctAnswers++;
                    }
                }
                if (correctAnswers == tab.getAnswers().size()) {
                    return question.getMark();
                }
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public List<UserAnswer> getUserAnswersWithOwnAnswerToCheckByResultId(String resultId) {
        try {
            int result = Integer.valueOf(resultId);
            List<UserAnswer> userAnswers = userAnswerDAO.getUserAnswersByResultId(result);
            List<UserAnswer> userAnswersWithOwnAnswers = new LinkedList<UserAnswer>();
            for (UserAnswer userAnswer : userAnswers) {
                if (userAnswer.getOwnAnswer() != null) {
                    userAnswersWithOwnAnswers.add(userAnswer);
                }
            }
            return userAnswersWithOwnAnswers;
        } catch (NumberFormatException e1) {
        } catch (Exception e2) {
        }
        return null;
    }

    public boolean saveMarksForOwnAnswers(List<UserAnswer> userAnswers, String strResultId) {
        try {
            int resultId = Integer.valueOf(strResultId);
            Result result = resultDAO.find(resultId);
            int markForOwnAnswers = 0;
            for (UserAnswer userAnswer : userAnswers) {
                userAnswerDAO.update(userAnswer);
                markForOwnAnswers += userAnswer.getMark();
            }
            result.setMark(result.getMark() + markForOwnAnswers);
            result.setIsChecked(true);
            resultDAO.update(result);
            return true;
        } catch (NumberFormatException e1) {
        } catch (Exception e2) {
        }
        return false;
    }

    public List<UserAnswer> getUserAnswersByResultId(String strResultId){
        try {
            int resultId = Integer.valueOf(strResultId);
            Result result = resultDAO.find(resultId);
            return result.getUserAnswers();
        }catch (NumberFormatException e1){}
        catch (Exception e2){}
        return null;
    }

    public List<String> getUserAnswersByQuestionId(int questionId, List<UserAnswer> userAnswers){
        List<Answer> answers = answerService.getAnswersByQuestionId(String.valueOf(questionId));
        List<String> strUserAnswers = new LinkedList<String>();
        for (Answer answer : answers){
            if (answerIdExistInUserAnswers(answer.getId(), userAnswers)){
                strUserAnswers.add(answer.getAnswerText());
            }
        }
        return strUserAnswers;
    }

    private boolean answerIdExistInUserAnswers(int answerId, List<UserAnswer> userAnswers){
        boolean exist = false;
        for (UserAnswer userAnswer : userAnswers){
            if (userAnswer.getAnswerId() == answerId){
                exist = true;
            }
        }
        return exist;
    }

}
