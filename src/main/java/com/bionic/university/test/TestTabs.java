package com.bionic.university.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 * @author bogdan.ponomarchuk
 * @since 05-Aug-15
 */
@ManagedBean
@ViewScoped
public class TestTabs {

    private List<Question> tabs;

    private String answer;

    @PostConstruct
    public void init(){
        tabs = new ArrayList<Question>();
        List<String> answers = new ArrayList<String>();
        answers.add("answ1");
        answers.add("answ2");
        Question question1 = new Question("q1", answers);
        Question question2 = new Question("q2", answers);
        tabs.add(question1);
        tabs.add(question2);
    }

    public List<Question> getTabs() {
        return tabs;
    }

    public void setTabs(final List<Question> tabs) {
        this.tabs = tabs;
    }

    public void doStuff(){
        System.out.println("doStuff");
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(final String answer) {
        this.answer = answer;
    }

    public void submit(ActionEvent actionEvent) {
        System.out.println("submit");
    }
}
