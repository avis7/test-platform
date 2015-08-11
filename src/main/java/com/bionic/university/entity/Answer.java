package com.bionic.university.entity;

import javax.persistence.*;


@Entity
@NamedNativeQueries({
        @NamedNativeQuery(name = "getAnswersByQuestionId",
                query = "SELECT * FROM answer a WHERE a.question_id = :question",
                resultClass = Answer.class),
        @NamedNativeQuery(name = "getVisibleAnswersByQuestionId",
                query = "SELECT * FROM answer a WHERE a.question_id = :question AND a.archived=FALSE",
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
    @Column(name = "archived", nullable = false)
    private boolean archived;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer(String answerText, boolean isCorrect) {
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }

    public Answer(){}

    public int getId() {
        return id;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean getIsCorrect() {
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

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
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
                ", getIsCorrect=" + isCorrect +
                '}';
    }
}
