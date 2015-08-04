package com.bionic.university.jsf;

import com.bionic.university.entity.Test;
import com.bionic.university.model.TestRow;
import com.bionic.university.services.TestService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;


@SessionScoped
@ManagedBean(name= "testBean", eager = true)
public class TestBean {

    @Inject
    TestService testService;

    public List<TestRow> getTestRows() {
        return testService.getTestRows();
    }

    public String fillTestTable(){
        if(testService.fillTestTable())
            return "successful";
        return "unsuccessful";
    }

    public void setTestRowEditable(TestRow testRow){
        testRow.setEditable(true);
    }

    public String addTest(String testName, int duration, Date deadline, String categoryName){
        if (testService.addTest(testName, duration, deadline,categoryName))
            return "successful";
        return "unsuccessful";
    }

    public String deleteTest(Test test){
        if(testService.deleteTest(test))
            return "successful";
        return "unsuccessful";
    }

    public String deleteTest(TestRow testRow){
        if(testService.deleteTest(testRow.getTest()))
            return "successful";
        return "unsuccessful";
    }

    public String editTest(TestRow testRow, String testName, String categoryName, int duration, Date deadline){
           if(testService.editTest(testRow, testName, categoryName, duration, deadline))
            return "successful";
        return "unsuccessful";
    }

}
