package com.bionic.university.test;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by c266 on 10.08.2015.
 */
@ViewScoped
@ManagedBean
public class testImageBean {
    private String answer;


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
