package com.bionic.university.services;

import com.bionic.university.dao.AnswerDAO;
import com.bionic.university.entity.Answer;
import com.bionic.university.entity.Question;
import com.bionic.university.model.QuestionRow;
import org.primefaces.event.RowEditEvent;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by c266 on 28.07.2015.
 */
public class AnswerService {
    @Inject
    private AnswerDAO answerDAO;
    @Inject
    private QuestionService questionService;

    private HashMap<Question,List<Answer>> answers = new HashMap<Question, List<Answer>>();

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

    public boolean addAnswer(QuestionRow questionRow, String answerText, boolean isCorrect) {
        try {
            Answer answer = new Answer(answerText, isCorrect);
            answer.setQuestion(questionRow.getQuestion());
            answerDAO.save(answer);
            changeQuestionType(questionRow.getQuestion());
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

    public boolean deleteAnswer(Answer answer){
        try {
            if (answer.isArchived())
                answer.setArchived(false);
            else answer.setArchived(true);
            answerDAO.update(answer);
            changeQuestionType(answer.getQuestion());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean onRowEdit(RowEditEvent event) {
        try {
            answerDAO.update((Answer) event.getObject());
            changeQuestionType(((Answer) event.getObject()).getQuestion());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public HashMap<Question, List<Answer>> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<Question, List<Answer>> answers) {
        this.answers = answers;
    }

    public void fillAnswerTable(Question question){
    try{
        List<Answer> answer=getAnswersForQuestion(String.valueOf(question.getId()));
        answers.put(question, answer);
    }catch (Exception e){
        e.printStackTrace();
    }
}

    public void sortArchived(Question question){
        fillAnswerTable(question);
        Collections.sort(answers.get(question), new Comparator<Answer>() {
            public int compare(Answer o1, Answer o2) {
                return (Boolean.valueOf(o1.isArchived())).compareTo(Boolean.valueOf(o2.isArchived()));
            }
        });
    }
}
