package com.bionic.university.test;

import java.util.List;

/**
 * @author bogdan.ponomarchuk
 * @since 05-Aug-15
 */
public class Question {

    private String question;
    private List<String> answers;
   private String answer;
    public Question(final String question, final List<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(final String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(final List<String> answers) {
        this.answers = answers;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(final String answer) {
        this.answer = answer;
    }
}
