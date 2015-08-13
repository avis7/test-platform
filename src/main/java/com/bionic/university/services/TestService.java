package com.bionic.university.services;

import com.bionic.university.dao.TestDAO;
import com.bionic.university.entity.Test;
import com.bionic.university.model.TestRow;
import org.primefaces.event.RowEditEvent;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.*;

public class TestService {

    @Inject
    private TestDAO testDAO;

    private List<TestRow> testRows = new ArrayList<TestRow>();
    private boolean visible;

    public int addTest(String testName, Integer duration,
                           Date deadline, String categoryName) {
        try {
            testDAO.save(new Test(testName, duration,
                    deadline, categoryName));
            setVisible(false);
            fillTestTable();
            testRows = getTestRows();
            return 1;
        }catch (PersistenceException e){
            return 3;
        }
        catch (Exception e) {
            return 2;
        }
    }

    public List<Test> getVisibleTests(){
        return testDAO.getVisibleTests();
    }

    public TestDAO getTestDAO() {
        return testDAO;
    }

    public void setTestDAO(TestDAO testDAO) {
        this.testDAO = testDAO;
    }


    public boolean deleteTest(TestRow testRow) {
        try {
            if(testRow.getTest().isArchived())
                testRow.getTest().setArchived(false);
            else testRow.getTest().setArchived(true);
            testDAO.update(testRow.getTest());
            fillTestTable();
            testRows = getTestRows();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Test> getTests(){
        return   testDAO.findAll();
    }

    public boolean fillTestTable() {
        try {
            testRows.clear();
            List<Test> tests = testDAO.findAll();
            for (int i = 0; i < tests.size(); i++) {
                testRows.add(i, new TestRow(tests.get(i), false));
            }
            setArchived();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setArchived(){
        try {
            Collections.sort(testRows);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean onRowEdit(RowEditEvent event) {
        try {
            testDAO.update((((TestRow) event.getObject()).getTest()));
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

    public Test getTestByTestId(int testId){
        return testDAO.find(testId);
    }
}

