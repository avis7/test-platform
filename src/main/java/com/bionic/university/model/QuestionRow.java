package com.bionic.university.model;

import com.bionic.university.entity.Question;

public class QuestionRow implements Comparable {

    private Question question;
    private boolean answerVisible;

    public QuestionRow(Question question, boolean answerVisible) {
        this.question = question;
        this.answerVisible = answerVisible;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isAnswerVisible() {
        return answerVisible;
    }

    public void setAnswerVisible(boolean answerVisible) {
        this.answerVisible = answerVisible;
    }

    public int compareTo(Object obj) {
        QuestionRow tmp = (QuestionRow)obj;
        if(this.getQuestion().isArchived() && tmp.getQuestion().isArchived())
        return 0;
        if(this.getQuestion().isArchived())
            return 1;
        return -1;
    }
}
