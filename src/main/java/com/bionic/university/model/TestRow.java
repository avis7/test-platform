package com.bionic.university.model;

import com.bionic.university.entity.Test;

public class TestRow {

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
}
