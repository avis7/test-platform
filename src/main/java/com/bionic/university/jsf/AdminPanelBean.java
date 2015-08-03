package com.bionic.university.jsf;

import com.bionic.university.Test;
import com.bionic.university.services.TestService;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by c2612 on 29.07.2015.
 */
public class AdminPanelBean {

    @Inject
    TestService testService;

    private String oldName;
    private String newTestName;
    private Date duration;
    private Date deadline;

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewTestName() {
        return newTestName;
    }

    public void setNewTestName(String newTestName) {
        this.newTestName = newTestName;
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

/*    public String editTest(String testName){
        testService.editTest(oldName, newTestName, duration, deadline);
        return "0";
    }*/

/*    public String addTest(){
        testService.addTest(newTestName, duration, deadline);
        return "0";
    }*/

/*
    public String deleteTest(){
        testService.deleteTest(oldName);
        return "0";
    }
*/


}
