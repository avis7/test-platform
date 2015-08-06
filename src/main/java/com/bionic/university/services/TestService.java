package com.bionic.university.services;

import com.bionic.university.dao.TestDAO;
import com.bionic.university.entity.Test;
import com.bionic.university.model.TestRow;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by c266 on 28.07.2015.
 */
public class TestService {

    @Inject
    private TestDAO testDAO;

    private List<TestRow> testRows = new ArrayList<TestRow>();
    private boolean visible;

    public boolean addTest(String testName, Integer duration,
                           Date deadline, String categoryName) {
        try {
            testDAO.save(new Test(testName, duration,
                    deadline, categoryName));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public TestDAO getTestDAO() {
        return testDAO;
    }

    public void setTestDAO(TestDAO testDAO) {
        this.testDAO = testDAO;
    }


    public boolean deleteTest(TestRow testRow) {
        try {
            testDAO.delete(testRow.getTest());
            fillTestTable();
            testRows = getTestRows();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean fillTestTable() {
        try {
            testRows.clear();
            List<Test> tests = testDAO.findAll();
            for (int i = 0; i < tests.size(); i++) {
                testRows.add(i, new TestRow(tests.get(i), false));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean onRowEdit(RowEditEvent event) {
        try {
            FacesMessage msg = new FacesMessage("Test Edited",
                    ((TestRow) event.getObject()).getTest().getTestName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            testDAO.update((((TestRow) event.getObject()).getTest()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean onRowCancel(RowEditEvent event) {
        try {
            FacesMessage msg = new FacesMessage("Edit Cancelled",
                    ((TestRow) event.getObject()).getTest().getTestName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<TestRow> getTestRows() {
        return testRows;
    }

    public void setTestRows(List<TestRow> testRows) {
        this.testRows = testRows;
    }
}

