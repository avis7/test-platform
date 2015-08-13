package com.bionic.university.beans.mentor;

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
    private String userSurname;
    private boolean visible;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

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

    public List<User> getSearchedUsers() {
        return userService.getSearchedUsers(userSurname);
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
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "���� ��� ����������� " + ((User) event.getObject()).getFirstName() + " ����������", null));
                break;
            case 2:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "���������� " + ((User) event.getObject()).getFirstName() + " �� ��� ����", null));
                break;
            case 3:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "���� ��� ����������� " + ((User) event.getObject()).getFirstName() + " �� ����������", null));
                break;
        }

    }

    public String onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("³����",
                ((User) event.getObject()).getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "successful";
    }
}
