package com.bionic.university.entity;

import javax.persistence.*;


@Entity
@NamedNativeQueries({
        @NamedNativeQuery(name = "getUserAnswersByResultId",
                query = "SELECT * FROM user_answer ua WHERE ua.result_id = :result",
                resultClass = UserAnswer.class)
})
@Table(name = "user_answer")
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "answer_id", nullable = false)
    private int answerId;
    @Column(name = "own_answer")
    private String ownAnswer;
    @Column(name = "mark")
    private int mark;
    @ManyToOne
    @JoinColumn(name = "result_id")
    private Result result;

    public UserAnswer() {
    }

    public UserAnswer(int answerId) {
        this.answerId = answerId;
    }

    public int getId() {
        return id;
    }

    public int getAnswerId() {
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

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
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
