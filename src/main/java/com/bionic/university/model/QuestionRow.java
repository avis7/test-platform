package com.bionic.university.model;

import com.bionic.university.entity.Question;

public class QuestionRow {

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
}
