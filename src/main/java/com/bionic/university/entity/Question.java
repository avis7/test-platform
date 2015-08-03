package com.bionic.university.entity;

import javax.persistence.*;
import java.util.Collection;

@NamedNativeQueries({
        @NamedNativeQuery(name = "getQueryQuestionByTestId", query = "select * from question q where  q.test_id = :arg", resultClass = Question.class)})
@Entity
@Table(name = "question")
public class    Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "question", nullable = false)
    private String question;
    @Column(name = "mark")
    private int mark;
    @Column(name = "picture")
    private String picture;
    @Column(name = "is_open", nullable = false)
    private boolean isOpen;
    @Column(name = "is_multichoise", nullable = false)
    private boolean isMultichoise;
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;
    @OneToMany(mappedBy = "question")
    private Collection<Answer> answers;

    public Question() {
    }

    public Question(String question, int mark, boolean isOpen, boolean isMultichoise) {
        this.question = question;
        this.mark = mark;
        this.isOpen = isOpen;
        this.isMultichoise = isMultichoise;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isMultichoise() {
        return isMultichoise;
    }

    public void setIsMultichoise(boolean isMultichoise) {
        this.isMultichoise = isMultichoise;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", mark=" + mark +
                ", picture='" + picture + '\'' +
                ", isOpen=" + isOpen +
                ", isMultichoise=" + isMultichoise +
                ", test=" + test +
                ", answers=" + answers +
                '}';
    }
}
