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
    private String email = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");

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


    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage(userService.editUserRole((User) event.getObject(), Integer.valueOf(getSelectRole()), email)
                ,((User) event.getObject()).getEmail());
            FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
            FacesMessage msg = new FacesMessage("³���� ���� ���",
                    ((User) event.getObject()).getEmail());
            FacesContext.getCurrentInstance().addMessage(null, msg);
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

    public String callMentorPage(){
        return "mentor/mentorProfile?faces-redirect=true&email=" + email;
    }
}
