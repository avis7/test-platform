package com.bionic.university.beans.admin;

import com.bionic.university.services.TestParserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean(name = "importTestBean")
@ViewScoped
public class ImportTestBean {
    @Inject
    TestParserService parserService;

    public void importTest() {
        parserService.initialize("/javatest.xml");
        parserService.parseTestFile();
    }
}
