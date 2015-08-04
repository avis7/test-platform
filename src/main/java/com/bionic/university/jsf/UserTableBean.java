package com.bionic.university.jsf;


import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.User;
import com.bionic.university.model.UserRow;
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

    // boolean editable;
    // List<User> users = new ArrayList<User>();

     List<UserRow> userRows = new ArrayList<UserRow>();

    @Inject
    UserTableService userTableService;
    @Inject
    UserDAO userDAO;

    public void fillUserTable() {
        userRows.clear();
        List<User> userList = userDAO.findAll();
        for (int i = 0; i < userList.size(); i++) {
            userRows.add(i, new UserRow(userList.get(i), false));
        }
    }

    public void setUserRowEditable(UserRow userRow) {
        userRow.setIsEditable(true);
    }

    public void updateUserRowValue(int id) {

    }

    public List<UserRow> getUserRows() {
        return userRows;
    }

    //    public List<User> getUsers() {
//        return users;
//    }



//    public void userView(){
//        users.clear();
//        users = userTableService.getUserFromDB();
//    }
//
//    public boolean isEditable() {
//        return editable;
//    }
//
//    public void setEditable(boolean editable) {
//        this.editable = editable;
//    }
//
//    public String editAction(UserRow userRow) {
//       setEditable (true);
//        oldId =user.getId();
//        return null;
//    }


    public String saveAction(UserRow userRow) {
        System.out.println("I AM HERE -----------------------------------------------");
        userRow.getUser().setFirstName("lalka");
        userRow.setIsEditable(false);
        return null;
    }
}
