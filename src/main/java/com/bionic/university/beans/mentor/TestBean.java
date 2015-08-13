package com.bionic.university.beans.mentor;


import com.bionic.university.entity.Result;
import com.bionic.university.entity.Test;
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
        clearFields();
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
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Тест " + testName + " доданий", null));
                break;
            case 2:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "???? " + testName + " ?? ???????", null));
                break;
            case 3:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "???? " + testName + " ?? ??????? ?? ??. ????? ??'? ?????", null));
                break;
        }
    }

    public boolean checkable(Test test) {
        boolean checkable = false;
        for (Result result : test.getResults()) {
            if (result.isSubmited() && !result.isChecked()) {
                checkable = true;
                break;
            }
        }
        return checkable;
    }

    public String deleteTest(TestRow testRow) {
        if (testService.deleteTest(testRow)) {
            if (testRow.getTest().isArchived())
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "????" + testRow.getTest().getTestName() + "  ???????????", null));
            else FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "????" + testRow.getTest().getTestName() + " ??????????????", null));
            return "successful";
        }
        return "unsuccessful";
    }

    public String onRowEdit(RowEditEvent event) {
        if (testService.onRowEdit(event)) {
            FacesMessage msg = new FacesMessage("???? ??????",
                    ((TestRow) event.getObject()).getTest().getTestName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "successful";
        }
        return "unsuccessful";
    }

    public String onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("?????",
                ((TestRow) event.getObject()).getTest().getTestName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "successful";
    }

    public String viewCheckedResults(TestRow testRow) {
        return "viewCheckedResults?faces-redirect=true&testId=" + testRow.getTest().getId();
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "???? ???????", format.format(event.getObject())));
    }

    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }

    public String callManageUsers() {
        return "manageUsers?faces-redirect=true";
    }

    public String check(int testId) {
        return "checkResults?faces-redirect=true&testId=" + testId;
    }

    public String importTest() {
        return "importTest.xhtml?faces-redirect=true";
    }

    public void clearFields() {
        deadline = null;
        duration = null;
        testName = null;
        categoryName = null;
    }

    public String getQuestions(TestRow testRow) {
        return "testPage?faces-redirect=true&testId=" + testRow.getTest().getId();
    }

}
