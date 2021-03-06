package com.bionic.university.entity;

import javax.persistence.*;


@Entity
@NamedNativeQueries({
        @NamedNativeQuery(name = "getAnswersByQuestionId",
                query = "SELECT * FROM answer a WHERE a.question_id = :question",
                resultClass = Answer.class)
})
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "answer_text")
    private String answerText;
    @Column(name = "is_correct", nullable = false)
    private boolean isCorrect;
    @Column(name = "picture")
    private String picture;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;



    public Answer(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Answer(String answerText, boolean isCorrect) {
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }

    public Answer() {
    }

    public int getId() {
        return id;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswerText() {
        return answerText;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answerText='" + answerText + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
