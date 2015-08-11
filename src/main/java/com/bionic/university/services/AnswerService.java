package com.bionic.university.services;

import com.bionic.university.dao.AnswerDAO;
import com.bionic.university.entity.Answer;
import com.bionic.university.entity.Question;
import org.primefaces.event.RowEditEvent;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by c266 on 28.07.2015.
 */
public class AnswerService {
    @Inject
    private AnswerDAO answerDAO;
    @Inject
    private QuestionService questionService;

    private  List<Answer> answers;

    public List<Answer> getAnswersForQuestion(String stringQuestionId) {
        int questionId = Integer.valueOf(stringQuestionId);
        return answerDAO.getAnswersByQuestionId(questionId);
    }

    public boolean addAnswer(int questionId, String answerText, boolean isCorrect) {
        try {
            Answer answer = new Answer(answerText, isCorrect);
            Question question = questionService.getQuestionDAO().find(questionId);
            answerDAO.save(answer);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean changeQuestionType(Question question){
        try{
            int counter=0;
            List<Answer> answers = answerDAO.getVisibleAnswersByQuestionId((int) question.getId());
            if(answers.size()==0) {
                question.setIsOpen(true);
                question.setIsMultichoise(false);
            }else {
                question.setIsOpen(false);
                for(Answer answer: answers){
                    if(answer.getIsCorrect())
                        counter++;
                }
                if(counter>1)
                    question.setIsMultichoise(true);
                else question.setIsMultichoise(false);
            }
            questionService.getQuestionDAO().update(question);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean addAnswer(String testId,Question question, String answerText, boolean isCorrect) {
        try {
            Answer answer = new Answer(answerText, isCorrect);
            answer.setQuestion(question);
            answerDAO.save(answer);
            changeQuestionType(question);
            questionService.setQuestions(questionService.fillQuestionTable(testId));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateAnswer(int answerId, String answerText, boolean isCorrect){
        Answer answer = answerDAO.find(answerId);
        answer.setAnswerText(answerText);
        answer.setIsCorrect(isCorrect);
        answerDAO.save(answer);
        return true;
    }

    public AnswerDAO getAnswerDAO() {
        return answerDAO;
    }

    public void setAnswerDAO(AnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }

    public boolean deleteAnswer(String testId,Answer answer){
        try {
            if (answer.isArchived())
                answer.setArchived(false);
            else answer.setArchived(true);
            answerDAO.update(answer);
            changeQuestionType(answer.getQuestion());
            questionService.setQuestions(questionService.fillQuestionTable(testId));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean onRowEdit(String testId, RowEditEvent event) {
        try {
            answerDAO.update((Answer) event.getObject());
            changeQuestionType(((Answer) event.getObject()).getQuestion());
            questionService.getQuestions();
            questionService.setQuestions(questionService.fillQuestionTable(testId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Answer> getAnswers(Question question){
        try{
            if(answers==null)
           answers=getAnswersForQuestion(String.valueOf(question.getId()));
            return  answers;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
