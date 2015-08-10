package com.bionic.university.beans.admin;

import com.bionic.university.services.TestParserService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.event.FileUploadEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@ManagedBean(name = "importTestBean")
@RequestScoped
public class ImportTestBean {
    private UploadedFile uploadedFile;

    @Inject
    TestParserService parserService;

    String filename;

//    private String getFilename(Part part) {
//        for (String cd : part.getHeader("content-disposition").split(";")) {
//            if (cd.trim().startsWith("filename")) {
//                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
//                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
//            }
//        }
//        return null;
//    }

    public String save() throws IOException {
        if (uploadedFile!= null) {
            filename = FilenameUtils.getName(uploadedFile.getFileName());
            InputStream input = uploadedFile.getInputstream();
            File testFile = new File("/resources/tests", filename);
            OutputStream output = new FileOutputStream(new File("/resources/tests", filename));

            parserService.initialize("/resources/tests" + testFile.getName());
            parserService.parseTestFile();

            try {
                IOUtils.copy(input, output);
            } finally {
                IOUtils.closeQuietly(input);
                IOUtils.closeQuietly(output);
            }

            return "/import?faces-redirect=true&success=" + true;
        }
        else {
            return null;
        }
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }
}
