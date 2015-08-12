package com.bionic.university.services;

import com.bionic.university.dao.ResultDAO;
import com.bionic.university.dao.RoleDAO;
import com.bionic.university.dao.UserDAO;
import com.bionic.university.entity.Result;
import com.bionic.university.entity.Test;
import com.bionic.university.entity.User;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserService {


    @Inject
    private RoleDAO roleDAO;
    @Inject
    private UserDAO userDAO;
    @Inject
    private ResultDAO resultDAO;

    public boolean authorization(String email, String password) {
        try {
            User user = userDAO.findUserByEmail(email);
            return user.getPassword().equals(password);
        } catch (IllegalArgumentException e) {
            System.out.println("dfce");

        } catch (Exception e0) {
            System.out.println("frd");
        }
        return false;

    }

    public boolean submitTest(Test test, String email) {
        Date beginTime = new Date();
        try {
            int userId = userDAO.findUserByEmail(email).getId();
            int testId = test.getId();
            Result result = resultDAO.findResultByUserIdAndTestId(userId, testId);
            if (result.isSubmited()) {
                return false;
            }
            result.setSubmited(true);
            result.setBeginTime(beginTime);
            resultDAO.update(result);
            return true;
        } catch (Exception e) {
            System.out.println("rfgb");
        }
        return false;
    }

    public List<User> getAllUsers() {
        try {
            return userDAO.findStudents();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public User findUserByEmail(String email) {
        try {
            return userDAO.findUserByEmail(email);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Test> getUserTests(String email) {
        ArrayList<Test> availableTests = new ArrayList<Test>();
        try {
            User user = userDAO.findUserByEmail(email);
            for (Result result : user.getResults()) {
                if (!result.isSubmited() && !result.getTest().isArchived() && result.getTest().getDeadline().after(new Date())) {
                    availableTests.add(result.getTest());
                }
            }
            return availableTests;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Result> getUserResults(String email) {
        ArrayList<Result> results = new ArrayList<Result>();
        try {
            User user = userDAO.findUserByEmail(email);
            for (Result result : user.getResults()) {
                if (result.isSubmited() && result.isChecked()) {
                    results.add(result);
                }
            }
            return results;
        } catch (Exception e) {
            return null;
        }
    }


    public UserDAO getUserDAO() {
        return userDAO;
    }

    public String editUserRole(User user, int newRoleValue, String email) {
        if (newRoleValue == user.getRole().getId()) return "Ви обрали роль, яка вже була у користувача";
        if (user.getRole().getId() == 1 && user.getEmail().compareTo(email) == 0)
            return "Ви не можете змінити собі роль";
        try {
            user.setRole(roleDAO.find(Integer.valueOf(newRoleValue)));
            userDAO.update(user);
            return "Роль змінена";
        } catch (Exception e) {
            return "Помилка бази даних";
        }
    }

    public List<User> getSearchedUsers(String userSurname){
        try {
            List<User> userList = new ArrayList<User>();
            List<User> users = getAllUsers();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getLastName().toLowerCase().contains(userSurname.toLowerCase()))
                    userList.add(userList.size(), users.get(i));
            }
            return userList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
