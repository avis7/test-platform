package com.bionic.university.entity;

import javax.persistence.*;


@Entity
@Table(name = "user_answer")
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "answer_id", nullable = false)
    private int answerId;
    @Column(name = "own_answer")
    private String ownAnswer;
    @ManyToOne
    @JoinColumn(name = "result_id")
    private Result result;

    public UserAnswer() {
    }

    public UserAnswer(int answerId) {
        this.answerId = answerId;
    }

    public long getId() {
        return id;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getOwnAnswer() {
        return ownAnswer;
    }

    public void setOwnAnswer(String ownAnswer) {
        this.ownAnswer = ownAnswer;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "UserAnswer{" +
                "id=" + id +
                ", answerId=" + answerId +
                ", ownAnswer='" + ownAnswer + '\'' +
                ", result=" + result +
                '}';
    }
}
