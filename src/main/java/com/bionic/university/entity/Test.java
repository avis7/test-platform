package com.bionic.university.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@NamedNativeQueries({
        @NamedNativeQuery(name = "getVisibleTests",
                query = "SELECT * FROM test t WHERE t.archived = FALSE ",
                resultClass = Test.class)})

@Entity
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "test_name", nullable = false, unique = true)
    private String testName;
    @Column(name = "duration", nullable = false)
    private int duration;
    @Column(name = "deadline", nullable = false)
    private Date deadline;
    @Column(name = "category_name", nullable = false)
    private String categoryName;
    @Column(name="archived",nullable = false)
    private boolean archived;
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @ManyToMany(mappedBy = "tests", fetch = FetchType.EAGER)
    private List<User> users;
    @OneToMany(mappedBy = "test")
    private List<Result> results;
    @OneToMany(mappedBy = "test", fetch = FetchType.EAGER)
    private List<Question> questions;

    public Test() {
    }

    public Test(String testName, int duration, Date deadline, String categoryName) {
        this.testName = testName;
        this.duration = duration;
        this.deadline = deadline;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
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
