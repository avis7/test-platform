package com.bionic.university.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;

@NamedNativeQueries({
        @NamedNativeQuery(name = "getQueryTestByUserId", query = "select * from test t where t.id = :arg", resultClass = Test.class)})
@Entity
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "test_name", nullable = false)
    private String testName;
    @Column(name = "duration", nullable = false)
    private Date duration;
    @Column(name = "deadline", nullable = false)
    private Date deadline;
    @Column(name = "category_name", nullable = false)
    private String categoryName;
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @ManyToMany(mappedBy = "tests", fetch = FetchType.EAGER)
    private Collection<User> users;
    @OneToMany(mappedBy = "test")
    Collection<Result> results;
    @OneToMany(mappedBy = "test")
    private Collection<Question> questions;

    public Test() {
    }

    public Test(String testName, Date duration, Date deadline) {
        this.testName = testName;
        this.duration = duration;
        this.deadline = deadline;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getId() {
        return id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<Result> getResults() {
        return results;
    }

    public void setResults(Collection<Result> results) {
        this.results = results;
    }

    public Collection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", testName='" + testName + '\'' +
                ", duration=" + duration +
                ", deadline=" + deadline +
                '}';
    }
}
