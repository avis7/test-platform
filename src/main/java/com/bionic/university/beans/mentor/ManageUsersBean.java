package com.bionic.university.beans.mentor;

import com.bionic.university.dao.ResultDAO;
import com.bionic.university.dao.TestDAO;
import com.bionic.university.entity.Result;
import com.bionic.university.entity.Test;
import com.bionic.university.entity.User;
import com.bionic.university.services.ResultService;
import com.bionic.university.services.TestService;
import com.bionic.university.services.UserService;
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
    private UserService userService;
    @Inject
    private TestService testService;
    @Inject
    private ResultService resultService;

    private List<User> users;
    private int selectedTest;

    public int getSelectedTest() {
        return selectedTest;
    }

    public void setSelectedTest(int selectedTest) {
        this.selectedTest = selectedTest;
    }

    public List<User> getUsers() {
        users = userService.getAllUsers();
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Test> getTests() {
        return testService.getTests();
    }

    public List<Test> getVisibleTests() {
        return testService.getVisibleTests();
    }

    public void onRowEdit(RowEditEvent event) {

        switch (resultService.onRowEdit(event, selectedTest)) {
            case 1:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Test for user "+((User)event.getObject()).getFirstName()+" was added", null));
            break;
            case 2:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "User "+((User)event.getObject()).getFirstName()+" has this test", null));
                break;
            case 3:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Test for user "+((User)event.getObject()).getFirstName()+" wasn't added", null));
        }

    }

    public String onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled",
                ((User) event.getObject()).getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "successful";
    }
}
