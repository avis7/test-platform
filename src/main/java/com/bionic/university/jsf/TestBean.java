package com.bionic.university.jsf;

import com.bionic.university.dao.TestDAO;
import com.bionic.university.entity.Test;
import com.bionic.university.model.TestRow;
import com.bionic.university.services.TestService;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;


@ViewScoped
@ManagedBean
public class TestBean {

    @Inject
    TestService testService;
    @Inject
    TestDAO testDAO;

    List<TestRow> testRows;

    long id;

    public List<TestRow> getTestRows() {
        return testRows;
    }
      @PostConstruct
    public void fillTestTable(){
        testService.fillTestTable();
         testRows = testService.getTestRows();
    }

    public String setTestRowEditable(int testRow){
        testRows.get(testRow).setEditable(true);
        return "";
    }

    public String addTest(String testName, int duration, Date deadline, String categoryName){
        if (testService.addTest(testName, duration, deadline,categoryName))
            return "successful";
        return "unsuccessful";
    }

    /*public String deleteTest(Test test){
        testService.deleteTest(test);
            return "";
    }*/

    public String deleteTest(TestRow testRow){
        testService.deleteTest(testRow.getTest());
        testService.fillTestTable();
        testRows = testService.getTestRows();
        return "";
    }
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Test Edited",
                ((TestRow) event.getObject()).getTest().getTestName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        testDAO.update((((TestRow) event.getObject()).getTest()));
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled",
                ((TestRow) event.getObject()).getTest().getTestName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public String editTest(int index){
        testRows.get(index).setEditable(false);
          // testService.editTest(testRow, testName, categoryName, duration, deadline);
           return "";
    }

}
