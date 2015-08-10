package com.bionic.university.beans.mentor;


import com.bionic.university.model.TestRow;
import com.bionic.university.services.TestService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@ViewScoped
@ManagedBean(name = "testsBean")
public class TestBean {


    @Inject
    TestService testService;

    private Date deadline;
    private Integer duration;
    private String testName;
    private String categoryName;

    public boolean isVisible() {
        return testService.getVisible();
    }

    public void setVisible() {
        testService.setVisible(true);
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<TestRow> getTestRows() {
        return testService.getTestRows();
    }

    @PostConstruct
    public void fillTestTable() {
        testService.fillTestTable();
    }

    public void addTest() {
        switch (testService.addTest(testName, duration, deadline, categoryName)) {
            case 1:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Test " + testName + " was added", null));
                        break;
            case 2:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Test " + testName + " was not added", null));
                break;
            case 3:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Test " + testName + " was not added to DB. Check unique of test name", null));
                break;
        }
    }

    public String deleteTest(TestRow testRow) {
        if (testService.deleteTest(testRow)) {
            if (testRow.getTest().isArchived())
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Test" + testRow.getTest().getTestName() + " was archived", null));
            else FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Test" + testRow.getTest().getTestName() + " was unarchived", null));
            return "successful";
        }
        return "unsuccessful";
    }

    public String onRowEdit(RowEditEvent event) {
        if (testService.onRowEdit(event)) {
            FacesMessage msg = new FacesMessage("Test Edited",
                    ((TestRow) event.getObject()).getTest().getTestName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "successful";
        }
        return "unsuccessful";
    }

    public String onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled",
                ((TestRow) event.getObject()).getTest().getTestName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "successful";
    }

    public String viewCheckedResults(TestRow testRow) {
        return "viewCheckedResults?faces-redirect=true&testId=" + String.valueOf(testRow.getTest().getId()) + "&testName=" + testRow.getTest().getTestName();
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }

    public String callManageUsers(){
           return "manageUsers";
    }
}
