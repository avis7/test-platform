package com.bionic.university.model;

import com.bionic.university.entity.Test;

public class TestRow implements Comparable {

    private Test test;
    private boolean editable;

    public TestRow(Test test, boolean editable) {
        this.test = test;
        this.editable = editable;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public int compareTo(Object obj) {
        TestRow tmp = (TestRow)obj;
        if(this.getTest().isArchived() && tmp.getTest().isArchived())
            return 0;
        if(this.getTest().isArchived())
            return 1;
        return -1;
    }
}
