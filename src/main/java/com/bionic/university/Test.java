package com.bionic.university;

import com.bionic.university.dao.RoleDAO;
import com.bionic.university.dao.UserDAO;

/**
 * @author bogdan.ponomarchuk
 * @since 02-Jul-15
 */
public class Test {
    public static void main(String[] args) {
        String s = "grifan_91@mail.ru";
        s.matches("[\\w*+@+\\w*.+\\w]");
    }
}
