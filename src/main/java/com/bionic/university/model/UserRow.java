package com.bionic.university.model;

import com.bionic.university.entity.User;

public class UserRow {
    private User user;
    private boolean isEditable;

    public UserRow(User user, boolean isEditable) {
        this.user = user;
        this.isEditable = isEditable;
    }

    public User getUser() {
        return user;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }
}
