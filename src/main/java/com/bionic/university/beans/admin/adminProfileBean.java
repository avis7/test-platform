package com.bionic.university.beans.admin;

import com.bionic.university.entity.User;
import com.bionic.university.services.UserService;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rondo104 on 05.08.2015.
 */
@ManagedBean(name ="adminProfileBean")
@ViewScoped
public class AdminProfileBean implements Serializable {
    private Set<String> roles = new HashSet<String>();
    private List<User> users;
    private String selectRole;

    @Inject
    UserService userService;


    public List<User> getUsers() {
        users = userService.getAllUsers();
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Set<String> getRoles() {
        for(User user: users){
            roles.add(user.getRole().getName()) ;
        }
        return roles;
    }


    public void onCellEdit(CellEditEvent event){
        String oldValue = (String) event.getOldValue();
        String newValue = (String) event.getNewValue();
        User editUser = users.get(event.getRowIndex());
        if(newValue != null && !newValue.equals(oldValue)) {
            boolean confirmation =  userService.editUserRole(editUser, newValue);
            FacesMessage msg;
            if (confirmation) {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            }else {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Problems with database connections", "Old: " + oldValue + ", New:" + newValue);
            }
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onRowEdit(RowEditEvent event) {
        System.out.println("222");
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getSelectRole() {
        return selectRole;
    }

    public void setSelectRole(String selectRole) {
        this.selectRole = selectRole;
    }
}
