package com.bionic.university.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;
import java.util.List;


@Entity
@NamedNativeQueries({
        @NamedNativeQuery(name = "findResultByUserIdAndTestId",
                query = "SELECT * FROM result r WHERE r.user_id = :user AND r.test_id = :test",
                resultClass = Result.class),
        @NamedNativeQuery(name = "findResultsByTestId",
                query = "SELECT * FROM result r WHERE r.test_id = :test",
                resultClass = Result.class)

})
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "is_checked")
    private boolean isChecked;
    @Column(name = "submited")
    private boolean submited;
    @Column(name = "mark")
    private int mark;
    @Column(name = "begin_time")
    private Date beginTime;
    @Column(name = "pass_time")
    private Date passTime;
    @Column(name = "feedback")
    private String feedback;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;
    @OneToMany(mappedBy = "result", fetch = FetchType.EAGER)
    List<UserAnswer> userAnswers;

    public Result() {
    }

    public Result(User user, Test test) {
        this.user = user;
        this.test = test;
    }

    public long getId() {
        return id;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean isSubmited() {
        return submited;
    }

    public void setSubmited(boolean submited) {
        this.submited = submited;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getPassTime() {
        return passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", isChecked=" + isChecked +
                ", submited=" + submited +
                ", mark=" + mark +
                ", beginTime=" + beginTime +
                ", passTime=" + passTime +
                ", feedback='" + feedback + '\'' +

                '}';
    }
}
