package com.bionic.university.beans.admin;

import com.bionic.university.entity.Role;
import com.bionic.university.entity.User;
import com.bionic.university.services.RoleService;
import com.bionic.university.services.UserService;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@ManagedBean(name ="adminProfileBean")
@ViewScoped
public class AdmProfileBean implements Serializable {
    private Set<Role> roles;
    private List<User> users;
    private String selectRole;

    @Inject
    UserService userService;
    @Inject
    RoleService roleService;


    public List<User> getUsers() {
        users = userService.getAllUsers();
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Role> getRoles() {
        return roleService.findAllRole();
    }


    public boolean onRowEdit(RowEditEvent event) {
        try {
            userService.editUserRole((User) event.getObject(), getSelectRole());
            FacesMessage msg = new FacesMessage("Role Edited",((User) event.getObject()).getRole().getName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean onRowCancel(RowEditEvent event) {
        try {
            System.out.println(" --- !!! --- IN ONROwCancel");
            FacesMessage msg = new FacesMessage("Edit Cancelled",
                    ((User) event.getObject()).getFirstName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getSelectRole() {
        return selectRole;
    }

    public void setSelectRole(String selectRole) {
        this.selectRole = selectRole;
    }
}
