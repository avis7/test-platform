package com.bionic.university.jsf;


import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.User;
import com.bionic.university.services.UserTableService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rondo104 on 28.07.2015.
 */
@SessionScoped
@ManagedBean (name = "userTableBean", eager = true)
public class UserTableBean {

    private long oldName;
    boolean editable;
    List<User> users = new ArrayList<User>();

    @Inject
    UserTableService userTableService;
    @Inject
    UserDAO userDAO;


    public List<User> getUsers() {
        return users;
    }

    public void userView(){
        users.clear();
        users = userTableService.getUserFromDB();
    }
    public boolean isEditable(){
        return editable;
    }

    public void setEditable (boolean editable){
        this.editable=editable;
    }

    public String editAction(User user) {
        setEditable(true);
        oldName=user.getId();
        return null;
    }

    public long getOldName() {
        return oldName;
    }

    public String saveAction() {
User user = userDAO.find(oldName);
        user.setFirstName("lalka");
        userDAO.update(user);
        setEditable(false);

        return null;

    }
}
