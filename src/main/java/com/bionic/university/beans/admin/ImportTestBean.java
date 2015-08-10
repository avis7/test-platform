package com.bionic.university.beans.admin;

import com.bionic.university.services.TestParserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.*;

@ManagedBean(name = "importTestBean")
@RequestScoped
public class ImportTestBean {
    private UploadedFile uploadedFile;

    @Inject
    TestParserService parserService;

    String filename;

    public String save() throws IOException {
        if (uploadedFile != null) {
            filename = FilenameUtils.getName(uploadedFile.getFileName());
            InputStream input = uploadedFile.getInputstream();
            String testDocument = IOUtils.toString(input, "UTF-8");

            parserService.initialize(testDocument);
            parserService.parseTestFile();

            FacesMessage message;

            if (parserService.containsNecessaryTagsAndAttributes()) {
                message = new FacesMessage("Файл тесту завантажено успішно");
            } else {
                message = new FacesMessage("Файл тесту не може бути завантажено. " +
                        "Неправильна структура XML, чи тест із такою назвою вже існує.");
            }
            FacesContext.getCurrentInstance().addMessage(null, message);

            return "/import?faces-redirect=true&success" + true;
        }

        else return null;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }
}
