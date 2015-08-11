package com.bionic.university.services;

import com.bionic.university.dao.QuestionDAO;
import com.bionic.university.entity.Question;
import com.bionic.university.model.QuestionRow;
import org.primefaces.event.RowEditEvent;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by c266 on 28.07.2015.
 */
public class QuestionService {
    @Inject
    private QuestionDAO questionDAO;
    @Inject
    private TestService testService;

    private boolean visibleQuestion;
    private List<QuestionRow> questionRows=new ArrayList<QuestionRow>();

    public List<QuestionRow> getQuestionRows() {
        return questionRows;
    }

    public void setQuestionRows(List<QuestionRow> questionRows) {
        this.questionRows = questionRows;
    }

    public boolean isVisibleQuestion() {
        return visibleQuestion;
    }

    public void setVisibleQuestion(boolean visibleQuestion) {
        this.visibleQuestion = visibleQuestion;
    }

    public QuestionDAO getQuestionDAO() {
        return questionDAO;
    }

    public void setQuestionDAO(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }
    
    public List<Question> getQuestionsByTestId(String stringTestId){
        try {
            int testId = Integer.valueOf(stringTestId);
            return questionDAO.getQuestionsByTestId(testId);
        }catch (NumberFormatException e1){}
        catch (Exception e2){}
        return null;
    }

    public boolean addQuestion(String questionText, int mark) {
        Question question = new Question(questionText, mark, false, false);
        questionDAO.save(question);
        return true;
    }


    public boolean updateQuestion(int questionId, String questionText, int mark, boolean isMultiChoice, boolean isOpen) {
        Question question = questionDAO.find(questionId);
        question.setQuestion(questionText);
        question.setMark(mark);
        question.setIsOpen(isOpen);
        question.setIsMultichoise(isMultiChoice);
        questionDAO.update(question);
        return true;
    }

    public Question getQuestionByAnswerId(int answerId){
        try {
            return questionDAO.getQuestionByAnswerId(answerId);
        }catch (Exception e){}
        return null;
    }

    public boolean deleteQuestion(QuestionRow questionRow) {
        try {
            Question question = questionRow.getQuestion();
            if (question.isArchived())
                question.setArchived(false);
            else question.setArchived(true);
            questionDAO.update(question);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addQuestion(String testId, String question, int mark, boolean isOpen, boolean isMultichoise) {
        try {
            Question quest = new Question(question, mark, isOpen, isMultichoise);
            quest.setTest(testService.getTestDAO().find(Integer.valueOf(testId)));
            questionDAO.save(quest);
            setVisibleQuestion(false);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean onRowEdit(String testId, RowEditEvent event) {
        try {
            ((QuestionRow) event.getObject()).getQuestion().setTest(testService.getTestDAO().find(Integer.valueOf(testId)));
            questionDAO.update((Question) event.getObject());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<QuestionRow> fillQuestionTable(String testId){
        try {
           questionRows.clear();
            List<Question> questions = getQuestionsByTestId(testId);
            for(int i =0; i<questions.size();i++){
                questionRows.add(i, new QuestionRow(questions.get(i), false));
            }
            return questionRows;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<QuestionRow> sortArchived(String testId){
        fillQuestionTable(testId);
        Collections.sort(questionRows);
        return questionRows;
    }
}
