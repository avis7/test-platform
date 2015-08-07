package com.bionic.university.beans.mentor;

import com.bionic.university.dao.ResultDAO;
import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.Result;
import com.bionic.university.entity.Test;
import com.bionic.university.entity.User;
import com.bionic.university.model.TestRow;
import com.bionic.university.services.TestService;
import com.bionic.university.services.UserService;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

@ManagedBean(name = "manageUsersBean")
@SessionScoped
public class ManageUsersBean {

    @Inject
    UserService userService;
    @Inject
    TestService testService;
    @Inject
    ResultDAO resultDAO;

    private List<User> users;
    private String selectedTests;

    public String getSelectedTests() {
        return selectedTests;
    }

    public void setSelectedTests(String selectedTests) {
        this.selectedTests = selectedTests;
    }

    public List<User> getUsers() {
        users = userService.getAllUsers();
        return users;
    }

    public void setUsers(List<User> users){
        this.users=users;
    }

    public List<Test> getTests(){
        return testService.getTests();
    }

    public boolean onRowEdit(RowEditEvent event) {
        try {
            FacesMessage msg = new FacesMessage("Test Edited",
                    ((User) event.getObject()).getFirstName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
//            resultDAO.save(new Result((User)event.getObject(),(Test)event.getObject()));
//            вот в этом вопрос
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean onRowCancel(RowEditEvent event) {
        try {
            FacesMessage msg = new FacesMessage("Edit Cancelled",
                    ((User) event.getObject()).getFirstName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
