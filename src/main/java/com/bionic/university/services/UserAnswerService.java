package com.bionic.university.services;

import com.bionic.university.dao.AnswerDAO;
import com.bionic.university.dao.ResultDAO;
import com.bionic.university.dao.UserAnswerDAO;
import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.Answer;
import com.bionic.university.entity.Question;
import com.bionic.university.entity.Result;
import com.bionic.university.entity.UserAnswer;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Olexandr on 8/1/2015.
 */
public class UserAnswerService {
    private List<Answer> userAnswers = new ArrayList<Answer>();
    private List<List<Answer>> userMultichoiseAnswers = new ArrayList<List<Answer>>();
    private Map<Answer, String> ownUserAnswers = new HashMap<Answer, String>();

    @Inject
    private UserAnswerDAO userAnswerDAO;
    @Inject
    private AnswerDAO answerDAO;
    @Inject
    private ResultDAO resultDAO;
    @Inject
    private UserDAO userDAO;

    public void addUserAnswer(Answer answer) {
        Question question = answer.getQuestion();
        List<Answer> allAnswers = question.getAnswers();
        for (Answer tempAnswer : allAnswers) {
            if (userAnswers.contains(tempAnswer)) {//!!!!!!!!!!
                userAnswers.remove(tempAnswer);
            }
        }
        userAnswers.add(answer);
    }

    public void addUserAnswer(Answer answer, String ownAnswer) {
        ownUserAnswers.put(answer, ownAnswer);
    }

    //!!!!!!!dangerous
    public void addUserAnswer(List<Answer> answers) {
        if (answers.isEmpty()) {
            return;
        }
        Answer answer = answerDAO.find(answers.get(0).getId());
        Question question = answer.getQuestion();
        List<Answer> allAnswers = question.getAnswers();
        for (List<Answer> answerList : userMultichoiseAnswers) {
            for (Answer tempAnswer : allAnswers) {
                if (answerList.contains(tempAnswer)) {
                    userMultichoiseAnswers.remove(answerList);
                }
            }
        }
        userMultichoiseAnswers.add(answers);
    }

    public boolean save(String email, String testId) {
        int test = Integer.valueOf(testId);
        int user = userDAO.findUserByEmail(email).getId();
        for (Answer answer : userAnswers) {
            UserAnswer userAnswer = new UserAnswer(answer.getId());
            Result result = resultDAO.findResultByUserIdAndTestId(user, test);
            result.setMark(calculateMark());
            result.setSubmited(true);
            userAnswer.setResult(result);
            resultDAO.update(result);
            userAnswerDAO.save(userAnswer);
        }
        for (Answer answer : ownUserAnswers.keySet()) {
            UserAnswer userAnswer = new UserAnswer(answer.getId());
            Result result = resultDAO.findResultByUserIdAndTestId(user, test);
            result.setSubmited(true);
            userAnswer.setResult(result);
            userAnswer.setOwnAnswer(ownUserAnswers.get(answer));
            resultDAO.update(result);
            userAnswerDAO.save(userAnswer);
        }
        return true;
    }

    public int calculateMark() {
        int mark = 0;
        for (Answer answer : userAnswers) {
            Question question = answer.getQuestion();
            if (answer.isCorrect()) {
                mark += question.getMark();
            }
        }
        for (List<Answer> answerList : userMultichoiseAnswers) {
            Question question = null;
            int countOfCorrectQuestions = 0;
            boolean isCorrect = true;
            for (Answer answer : answerList) {
                if (!answer.isCorrect()) {
                    isCorrect = false;
                    break;
                }
                if (question == null) {
                    question = answer.getQuestion();
                    List<Answer> answers = question.getAnswers();
                    for (Answer tempAnswer : answers) {
                        if (tempAnswer.isCorrect()) {
                            countOfCorrectQuestions++;
                        }
                    }
                }
            }
            if (isCorrect && countOfCorrectQuestions == answerList.size()) {
                mark += question.getMark();
            }
        }
        return mark;
    }


}
